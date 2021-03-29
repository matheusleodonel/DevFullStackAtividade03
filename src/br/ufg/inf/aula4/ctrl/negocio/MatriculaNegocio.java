package br.ufg.inf.aula4.ctrl.negocio;

import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.aula4.ctrl.exception.AlunoException;
import br.ufg.inf.aula4.ctrl.exception.CursoException;
import br.ufg.inf.aula4.ctrl.exception.DisciplinaExection;
import br.ufg.inf.aula4.ctrl.exception.MatriculaException;
import br.ufg.inf.aula4.ctrl.exception.OfertaExection;
import br.ufg.inf.aula4.ctrl.exception.ProfessorExection;
import br.ufg.inf.aula4.model.dao.MatriculaDAO;
import br.ufg.inf.aula4.model.entities.Matricula;

public class MatriculaNegocio {
	
	MatriculaDAO dao = new MatriculaDAO();
	AlunoNegocio alunoNegocio = new AlunoNegocio();
	OfertaNegocio ofertaNegocio = new OfertaNegocio();

	public Matricula inserir(Matricula matricula) throws MatriculaException {
		this.validarMatricula(matricula);
		dao.inserir(matricula);
		return matricula;
	}
	
	// READ
	public List<Matricula> buscaTodos() throws MatriculaException, AlunoException, OfertaExection, CursoException, ProfessorExection, DisciplinaExection{
		List<Matricula> matriculas =  new ArrayList<Matricula>();
		for(Matricula o : dao.buscaTodos()) {
			o.setAluno(alunoNegocio.buscaPorId(o.getAluno().getIdAluno()));
			o.setOferta(ofertaNegocio.buscaPorId(o.getOferta().getIdOferta()));
		}
		return matriculas;	
	}
	
	public Matricula buscaPorId(Integer id) throws MatriculaException, AlunoException, OfertaExection, ProfessorExection, DisciplinaExection {
		Matricula matricula = new Matricula();
		matricula.setAluno(alunoNegocio.buscaPorId(matricula.getAluno().getIdAluno()));
		matricula.setOferta(ofertaNegocio.buscaPorId(matricula.getOferta().getIdOferta()));
		return matricula;
	}
	
	
	// UPDATE
	
	public Matricula alterar(Matricula matricula) throws MatriculaException, CursoException {		
		this.validarMatricula(matricula);
		return dao.alterar(matricula);
	}
	
	// DELETE
	
	public void excluir(Integer id) throws MatriculaException {
		dao.excluir(id);
	}
	
	private void validarMatricula(Matricula matricula) throws MatriculaException {
		

	}
}
