document.addEventListener("DOMContentLoaded", function () {
    const logoutBtn = document.getElementById("logoutBtn");
    const loginBtn = document.getElementById("loginBtn");

    // Verificamos si hay sesión con el endpoint que creamos
    fetch('/api/usuarios/session-status')
            .then(res => {
                if (res.ok) {
                    // Hay sesión activa
                    loginBtn.classList.add('d-none');
                    logoutBtn.classList.remove('d-none');
                } else {
                    // No hay sesión
                    loginBtn.classList.remove('d-none');
                    logoutBtn.classList.add('d-none');
                }
            });

    logoutBtn.addEventListener("click", function (e) {
        e.preventDefault();
        fetch('/api/usuarios/logout', {method: 'POST'})
                .then(() => {
                    window.location.href = '/login';
                });
    });
});

let clienteIdActual = null;

function buscarClienteDni() {
    const dni = document.getElementById('dni').value.trim();
    const comisionPendiente = 214;

    if (dni) {
        fetch(`/api/clientes/dni/${dni}`)
                .then(response => {
                    if (!response.ok)
                        throw new Error();
                    return response.json();
                })
                .then(cliente => {
                    clienteIdActual = cliente.id_usuario;
                    document.getElementById('datosCliente').style.display = 'block';

                    document.getElementById('clienteId').textContent = cliente.id_usuario;
                    document.getElementById('clienteNombre').textContent = cliente.nombre;
                    document.getElementById('clienteApellidos').textContent = cliente.apellidos;
                    document.getElementById('clienteDni').textContent = cliente.dni;
                    document.getElementById('clienteEmail').textContent = cliente.email;

                    document.getElementById('saldoDisponible').textContent = `S/ ${cliente.saldo_disponible.toFixed(2)}`;
                    document.getElementById('saldoContable').textContent = `S/ ${cliente.saldo_contable.toFixed(2)}`;

                    const operacionesTableBody = document.getElementById('operacionesTableBody');
                    operacionesTableBody.innerHTML = '';

                    const cantidadFilas = (cliente.saldo_contable === comisionPendiente * 2) ? 2 : 1;

                    for (let i = 0; i < cantidadFilas; i++) {
                        const fila = document.createElement('tr');
                        fila.innerHTML = `
        <td>REG PARCIAL S/ITF</td>
        <td>Abril 2025</td>
        <td>S/ ${comisionPendiente.toFixed(2)}</td>
        <td>
            <button class="btn btn-outline-danger btn-sm me-1" onclick="escalarAAnalista(this)">
                Escalar a Analista
            </button>
            <button class="btn btn-outline-success btn-sm" onclick="liberarOperacion(this)">
                Liberar
            </button>
        </td>
    `;
                        operacionesTableBody.appendChild(fila);
                    }

                    const modal = bootstrap.Modal.getInstance(document.getElementById('modalBuscarCliente'));
                    if (modal)
                        modal.hide();
                })
                .catch(() => {
                    Swal.fire('Error', 'Cliente no encontrado por DNI', 'error');
                    document.getElementById('datosCliente').style.display = 'none';
                    document.getElementById('saldoDisponible').textContent = `S/ 000.00`;
                    document.getElementById('saldoContable').textContent = `S/ 000.00`;
                    const operacionesTableBody = document.getElementById('operacionesTableBody');
                    if (operacionesTableBody)
                        operacionesTableBody.innerHTML = '';
                });
    } else {
        Swal.fire('Advertencia', 'Ingrese un DNI válido', 'warning');
    }
}

function escalarAAnalista(button) {
    button.innerHTML = '✅';
    button.disabled = true;

    // Deshabilitar botón "Liberar" de la misma fila
    const fila = button.closest('tr');
    const botonLiberar = fila.querySelector('button.btn-outline-success');
    if (botonLiberar) {
        botonLiberar.disabled = true;
    }
}

function liberarOperacion(button) {
    const montoLiberado = 214.00;

    if (clienteIdActual === null) {
        Swal.fire('Error', 'No se ha seleccionado un cliente.', 'error');
        return;
    }

    Swal.fire({
        title: '¿Estás seguro?',
        text: `Se liberarán S/ ${montoLiberado.toFixed(2)} al saldo disponible del cliente.`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, liberar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            const saldoDisponibleElem = document.getElementById('saldoDisponible');
            const saldoContableElem = document.getElementById('saldoContable');

            let saldoActualDisponible = parseFloat(saldoDisponibleElem.textContent.replace(/[^\d.-]/g, ''));
            let saldoActualContable = parseFloat(saldoContableElem.textContent.replace(/[^\d.-]/g, ''));

            const nuevoDisponible = saldoActualDisponible + montoLiberado;
            const nuevoContable = saldoActualContable - montoLiberado;

            // Enviar datos al backend
            fetch('/api/clientes/actualizar-saldo', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    idUsuario: clienteIdActual,
                    nuevoSaldoDisponible: nuevoDisponible,
                    nuevoSaldoContable: nuevoContable
                })
            })
            .then(res => {
                if (!res.ok) throw new Error();
                return res.text();
            })
            .then(() => {
                // Actualizar visualmente los saldos
                saldoDisponibleElem.textContent = `S/ ${nuevoDisponible.toFixed(2)}`;
                saldoContableElem.textContent = `S/ ${nuevoContable.toFixed(2)}`;

                // Remplazar ambos botones por texto
                const fila = button.closest('tr');
                fila.querySelector('td:last-child').innerHTML = `<span class="text-success fw-bold">Dinero liberado</span>`;

                Swal.fire({
                    icon: 'success',
                    title: 'Liberado',
                    text: 'El saldo fue actualizado correctamente.',
                    timer: 2000,
                    showConfirmButton: false
                });
            })
            .catch(() => {
                Swal.fire('Error', 'No se pudo actualizar el saldo.', 'error');
            });
        }
    });
}