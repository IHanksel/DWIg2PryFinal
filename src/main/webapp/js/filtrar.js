$(document).ready(function () {

    $(document).on('keyup', '#txtalumno', function () {
        let valor = $(this).val();
        listadoAlumnos(valor);
    });

    function listadoAlumnos(consulta) {
        opcion = "1";
        $.get("/SistemaUniversidad/ControlMantenimiento", {opcion, consulta}, (response) => {
            const misDatos = JSON.parse(response);
            let template = "";

            template += `
                <table class="table table-hover">
                    <thead class="text-white bg-black">
                        <tr>
                            <th>Código</th>
                            <th>Nombres</th>
                            <th>Apellidos</th>
                            <th>DNI</th>
                            <th>Dirección</th>
                            <th>Teléfono</th>
                            <th>Carrera</th>
                            <th>Modificar</th>
                            <th>Eliminar</th>
                        </tr>
                    </thead>
                    <tbody>`;

            misDatos.forEach(alumno => {
                template += `
                    <tr>
                        <td>${alumno.codigo}</td>
                        <td>${alumno.nombres}</td>
                        <td>${alumno.apellidos}</td>
                        <td>${alumno.dni}</td>
                        <td>${alumno.direccion}</td>
                        <td>${alumno.telefono}</td>
                        <td>${alumno.carrera}</td>
                        <td><a>Modificar</a></td>
                        <td><a href="#" onclick="eliminarAlumno('${alumno.codigo}')">Eliminar</a></td>
                    </tr>`;
            });

            template += `</tbody></table>`;
            $("#tablaresalumno").html(template);
        });
    }

});
