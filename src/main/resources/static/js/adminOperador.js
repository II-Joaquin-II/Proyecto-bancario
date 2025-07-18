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

    logoutBtn.addEventListener("click", function(e) {
      e.preventDefault();
      fetch('/api/usuarios/logout', { method: 'POST' })
        .then(() => {
          window.location.href = '/login';
        });
    });
  });