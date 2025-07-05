document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("loginForm");

    form.addEventListener("submit", function (e) {
        e.preventDefault();

        const email = document.getElementById("correo").value;
        const password = document.getElementById("password").value;

        fetch("/api/usuarios/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ email, password })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Datos incorrectos");
            }
            return response.json();
        })
        .then(data => {
            const roles = data.roles;
            const nombre = data.nombre;

            if (roles.includes("ROLE_OPERADOR")) {
                Swal.fire({
                    icon: 'success',
                    title: `Bienvenido ${nombre}`,
                    text: `Redirigiendo como OPERADOR...`
                }).then(() => {
                    window.location.href = "/adminOperador";
                });
            } else if (roles.includes("ROLE_ANALISTA")) {
                Swal.fire({
                    icon: 'success',
                    title: `Bienvenido ${nombre}`,
                    text: `Redirigiendo como ANALISTA...`
                }).then(() => {
                    window.location.href = "/adminAnalista";
                });
            } else {
                throw new Error("No tiene permisos para acceder.");
            }
        })
        .catch(error => {
            Swal.fire({
                icon: 'error',
                title: 'Error de inicio de sesión',
                text: error.message
            });
        });
    });
});
