package br.ufg.inf.aula4.ctrl;

import java.util.List;

import br.ufg.inf.aula4.ctrl.exception.AlunoException;
import br.ufg.inf.aula4.ctrl.exception.CursoException;
import br.ufg.inf.aula4.ctrl.exception.DisciplinaExection;
import br.ufg.inf.aula4.ctrl.exception.MatriculaException;
import br.ufg.inf.aula4.ctrl.exception.OfertaExection;
import br.ufg.inf.aula4.ctrl.exception.ProfessorExection;
import br.ufg.inf.aula4.ctrl.negocio.MatriculaNegocio;
import br.ufg.inf.aula4.model.entities.Matricula;

public class MatriculaCtrl {
	MatriculaNegocio negocio = new MatriculaNegocio();

	public Matricula inserir(Matricula matricula) {
		try {
			matricula = negocio.inserir(matricula);
			System.out.println("Matricula realizada com sucesso: " + matricula);
		} catch (MatriculaException e) {
			System.out.println("Erro ao tentar realizar matrícula.");
			System.out.println(e.getMessage());
		}
		return matricula;
	}

	public List<Matricula> buscaTodos() throws CursoException, ProfessorExection, DisciplinaExection {
		List<Matricula> matriculas = null;
		try {
			matriculas = negocio.buscaTodos();
		} catch (MatriculaException e) {
			System.out.println("Erro tentar buscar as matriculas cadastradas.");
			System.out.println(e.getMessage());
		} catch (AlunoException e) {
			System.out.println("Erro tentar buscar as matriculas cadastradas.");
			System.out.println(e.getMessage());
		} catch (OfertaExection e) {
			System.out.println("Erro tentar buscar as matriculas cadastradas.");
			System.out.println(e.getMessage());
		}
		return matriculas;
	}

	public Matricula buscaPorId(Integer id) throws ProfessorExection, DisciplinaExection {
		Matricula matricula = null;
		try {
			matricula = negocio.buscaPorId(id);
		} catch (MatriculaException e) {
			System.out.println("Erro tentar buscar matricula de ID: " + id + ".");
			System.out.println(e.getMessage());
		} catch (AlunoException e) {
			System.out.println("Erro tentar buscar matricula de ID: " + id + ".");
			System.out.println(e.getMessage());
		} catch (OfertaExection e) {
			System.out.println("Erro tentar buscar matricula de ID: " + id + ".");
			System.out.println(e.getMessage());
		}
		return matricula;
	}

	public Matricula alterar(Matricula matricula) throws CursoException {
		try {
			matricula = negocio.alterar(matricula);
			System.out.println("Oferta alterado com sucesso: " + matricula);
		} catch (MatriculaException e) {
			System.out.println("Erro ao tentar alterar oferta com ID: " + matricula.getIdMatricula() + ".");
			System.out.println(e.getMessage());
		}
		return matricula;
	}

	public void excluir(Integer id) {
		try {
			negocio.excluir(id);
			System.out.println("Matricula excluída com sucesso.");
		} catch (MatriculaException e) {
			System.out.println("Erro ao tentar excluir a matricula");
			System.out.println(e.getMessage());
		}
	}
}
