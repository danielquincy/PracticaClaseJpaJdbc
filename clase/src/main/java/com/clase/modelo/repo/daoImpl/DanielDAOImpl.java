package com.clase.modelo.repo.daoImpl;

import com.clase.config.PersonaConfiguration;
import com.clase.modelo.entity.Daniel;
import com.clase.modelo.entity.Persona;
import com.clase.modelo.mapper.DanielMapper;
import com.clase.modelo.mapper.PersonaMapper;
import com.clase.modelo.repo.dao.DanielDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DanielDAOImpl implements DanielDAO {
    private final JdbcTemplate oJdbcTemplate;

    @Autowired
    public DanielDAOImpl(JdbcTemplate oJdbcTemplate) {
        this.oJdbcTemplate = oJdbcTemplate;
    }
    public Daniel obtenerPorId(Integer prId) {
        return oJdbcTemplate.queryForObject("select * from DANIEL where id = ?",new DanielMapper(), prId);
    }

    public List<Daniel> obtenerTodo() {
        return oJdbcTemplate.query("select * from Daniel", new DanielMapper());
    }

    public void guardar(Daniel oDaniel) {
        oJdbcTemplate.update("INSERT INTO [dbo].[DANIEL] (" +
                        "[NOMBRE_1]," +
                        "[NOMBRE_2]," +
                        "[APELLIDO_1]," +
                        "[APELLIDO_2] ," +
                        "[CEDULA]," +
                        "[EDAD]," +
                        "[SEXO])" +
                        " values (?,?,?,?,?,?,?)",
                oDaniel.getNombre1(),
                oDaniel.getNombre2(),
                oDaniel.getApellido1(),
                oDaniel.getApellido2(),
                oDaniel.getCedula(),
                oDaniel.getEdad(),
                oDaniel.getSexo());
    }

    public Daniel guardarJdbc(Daniel oDaniel) {
        oJdbcTemplate.update("INSERT INTO [dbo].[DANIEL] (" +
                        "[NOMBRE_1]," +
                        "[NOMBRE_2]," +
                        "[APELLIDO_1]," +
                        "[APELLIDO_2] ," +
                        "[CEDULA]," +
                        "[EDAD]," +
                        "[SEXO])" +
                        " values (?,?,?,?,?,?,?)",
                oDaniel.getNombre1(),
                oDaniel.getNombre2(),
                oDaniel.getApellido1(),
                oDaniel.getApellido2(),
                oDaniel.getCedula(),
                oDaniel.getEdad(),
                oDaniel.getSexo());

        return oJdbcTemplate.query("select top 1 * from Daniel order by 1 desc", new DanielMapper()).get(0);
    }

    public List<Daniel> guardarSimpleJdbcInsert(Daniel oDaniel) {
        DataSource oDataSource = new PersonaConfiguration().getDataSource();

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(oDataSource)
                .withTableName("DANIEL")
                .usingGeneratedKeyColumns("ID");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("NOMBRE_1", oDaniel.getNombre1());
        parameters.put("NOMBRE_2", oDaniel.getNombre2());
        parameters.put("APELLIDO_1", oDaniel.getApellido1());
        parameters.put("APELLIDO_2", oDaniel.getApellido2());
        parameters.put("CEDULA", oDaniel.getCedula());
        parameters.put("EDAD", oDaniel.getEdad());
        parameters.put("SEXO", oDaniel.getSexo());
        jdbcInsert.execute(parameters);

        return obtenerTodo();
    }

    public Daniel actualizarJdbc(Daniel oDaniel) {
        oJdbcTemplate.update("UPDATE [dbo].[DANIEL] SET " +
                        "[NOMBRE_1] = ?," +
                        "[NOMBRE_2] = ?," +
                        "[APELLIDO_1] = ?," +
                        "[APELLIDO_2] = ?," +
                        "[CEDULA] = ?," +
                        "[EDAD] =?," +
                        "[SEXO] = ?" +
                        " WHERE ID = ?",
                oDaniel.getNombre1(),
                oDaniel.getNombre2(),
                oDaniel.getApellido1(),
                oDaniel.getApellido2(),
                oDaniel.getCedula(),
                oDaniel.getEdad(),
                oDaniel.getSexo(),
                oDaniel.getId());

        return oJdbcTemplate.query("select * from Daniel", new DanielMapper())
                .stream()
                .filter(x -> x.getId().equals(oDaniel.getId()))
                .findFirst()
                .orElse(null);
    }

    public void borrarJdbc(Daniel oDaniel) {
        oJdbcTemplate.update("delete [dbo].[DANIEL] WHERE ID = ?",
                oDaniel.getId());
    }

    public List<Daniel> callProcedure(Daniel oDaniel) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(oJdbcTemplate)
                .withProcedureName("SP_TABLA_DANIEL")
                .returningResultSet("", new DanielMapper());

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("NOMBRE_1", oDaniel.getNombre1())
                .addValue("NOMBRE_2", oDaniel.getNombre2())
                .addValue("APELLIDO_1", oDaniel.getApellido1())
                .addValue("APELLIDO_2", oDaniel.getApellido2())
                .addValue("CEDULA", oDaniel.getCedula())
                .addValue("EDAD", oDaniel.getEdad())
                .addValue("SEXO", oDaniel.getSexo())
                .addValue("OPCION", 1) ;

        return (List<Daniel>) simpleJdbcCall.execute(in);
    }
}
