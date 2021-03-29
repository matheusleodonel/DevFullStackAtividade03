package br.ufg.inf.aula4.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.aula4.app.DB;
import br.ufg.inf.aula4.ctrl.exception.CursoException;
import br.ufg.inf.aula4.ctrl.exception.MatriculaException;
import br.ufg.inf.aula4.model.entities.Matricula;

public class MatriculaDAO {
	// CREATE

	public Matricula inserir(Matricula matricula) throws MatriculaException {
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			st = (PreparedStatement) conn.prepareStatement("INSERT INTO tb_matricula (id_aluno, id_oferta) VALUES (?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, matricula.getAluno().getIdAluno());
			st.setInt(2, matricula.getOferta().getIdOferta());
			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);

			if (rowsAffected > 0) {

				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					matricula.setIdMatricula(id);
				}
			}
		} catch (SQLException e) {
			throw new MatriculaException(e.getMessage());
		}

		return matricula;
	}

	// READ

	public List<Matricula> buscaTodos() throws CursoException {
		ResultSet rs = null;
		PreparedStatement st = null;
		List<Matricula> matriculas = new ArrayList<Matricula>();
		try {
			Connection conn = DB.getConnection();
			st = conn.prepareStatement("SELECT * FROM tb_curso ORDER BY nm_curso ");

			rs = st.executeQuery();

			while (rs.next()) {
				Matricula matricula = new Matricula();

				matricula.setIdMatricula(rs.getInt("id_matricula"));
				matricula.getAluno().setIdAluno(rs.getInt("id_aluno"));
				matricula.getOferta().setIdOferta(rs.getInt("id_matricula"));

				matriculas.add(matricula);
			}

		} catch (SQLException e) {
			throw new CursoException(e.getMessage());
		}

		return matriculas;
	}

	public Matricula buscaPorId(Integer id) throws MatriculaException {
		Matricula matricula = new Matricula();

		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			st = conn.prepareStatement("SELECT * FROM tb_matricula WHERE id_matricula = ? ");
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				matricula.setIdMatricula(rs.getInt("id_matricula"));
				matricula.getAluno().setIdAluno(rs.getInt("id_aluno"));
				matricula.getOferta().setIdOferta(rs.getInt("id_matricula"));
			}

		} catch (SQLException e) {
			throw new MatriculaException(e.getMessage());
		}

		return matricula;
	}

	// UPDATE

	public Matricula alterar(Matricula matricula) throws CursoException {
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();

			String query = "UPDATE tb_matricula SET " + " id_aluno = ? , id_oferta = ? " + " WHERE id_matricula = ? ; ";
			st = (PreparedStatement) conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, matricula.getAluno().getIdAluno());
			st.setInt(2, matricula.getOferta().getIdOferta());
			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);

		} catch (SQLException e) {
			throw new CursoException(e.getMessage());
		}

		return matricula;
	}

	// DELETE

	public void excluir(Integer id) throws MatriculaException {
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();

			String query = " DELETE FROM tb_matricula WHERE id_matricula = ? ;";
			st = (PreparedStatement) conn.prepareStatement(query);
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);

		} catch (SQLException e) {
			throw new MatriculaException(e.getMessage());
		}
	}
}
