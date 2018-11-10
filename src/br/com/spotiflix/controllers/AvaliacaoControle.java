package br.com.spotiflix.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.spotiflix.conection.Conexao;
import br.com.spotiflix.models.Avaliacao;

public class AvaliacaoControle {
	
	Conexao conect = new Conexao();
	Avaliacao modelo = new Avaliacao();

	public void salvar(Avaliacao modelo) {

		conect.conecxao();// conecta com o banco de dados
		try {
			PreparedStatement pst = conect.conecta.prepareStatement("insert into avaliacoes(id_usuario, id_banda, nota) values(?,?,?)");

			pst.setLong(1, modelo.getId_usuario());
			pst.setLong(2, modelo.getId_banda());
			pst.setFloat(3, modelo.getNota());

			pst.execute();
			JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir dados!\n" + ex);
		}

		conect.desconecxao();// desconecta com o banco de dados
	}

	public List<Avaliacao> read(String desc) {
		conect.conecxao();
		ResultSet rs = null;
		PreparedStatement stmt = null;

		List<Avaliacao> avaliacoes = new ArrayList<>();

		try {
			stmt = conect.conecta.prepareStatement("SELECT * FROM avaliacoes WHERE data LIKE ?");
			stmt.setString(1, "%" + desc + "%");

			rs = stmt.executeQuery();

			while (rs.next()) {

				Avaliacao avaliacao = new Avaliacao();

				avaliacao.setId(rs.getLong("id"));
				avaliacao.setId_usuario(rs.getLong("id_usuario"));
				avaliacao.setId_banda(rs.getLong("id_banda"));
				avaliacao.setNota(rs.getFloat("nota"));

				avaliacoes.add(avaliacao);

			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao pesquisar!\n" + ex.getMessage());

		}

		conect.desconecxao();
		return avaliacoes;

	}

	public List<Avaliacao> readAll() {
		conect.conecxao();
		ResultSet rs = null;
		PreparedStatement stmt = null;

		List<Avaliacao> avaliacoes = new ArrayList<>();

		try {
			stmt = conect.conecta.prepareStatement("SELECT * FROM avaliacoes");

			rs = stmt.executeQuery();

			while (rs.next()) {

				Avaliacao avaliacao = new Avaliacao();

				avaliacao.setId(rs.getLong("id"));
				avaliacao.setId_usuario(rs.getLong("id_usuario"));
				avaliacao.setId_banda(rs.getLong("id_banda"));
				avaliacao.setNota(rs.getFloat("nota"));


				avaliacoes.add(avaliacao);

			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao pesquisar!\n" + ex.getMessage());

		}

		conect.desconecxao();
		return avaliacoes;

	}

	public void update(Avaliacao modelo) {
		conect.conecxao();

		try {
			PreparedStatement stmt = conect.conecta
					.prepareStatement("UPDATE avaliacoes SET nota = ? WHERE id = ?");

			stmt.setFloat(1, modelo.getNota());
			

			stmt.execute();

			JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar dados!\n" + ex.getMessage());
		}

		conect.desconecxao();
	}

	public void delete(Avaliacao modelo) {

		conect.conecxao();

		PreparedStatement stmt = null;

		try {
			stmt = conect.conecta.prepareStatement("DELETE FROM avaliacoes WHERE id = ?");
			stmt.setLong(1, modelo.getId());

			stmt.execute();
			JOptionPane.showMessageDialog(null, "Dados deletado com sucesso!");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao deletar dados!\n" + ex.getMessage());
		}

	}

}
