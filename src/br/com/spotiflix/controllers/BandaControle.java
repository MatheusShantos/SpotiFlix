package br.com.spotiflix.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.spotiflix.conection.Conexao;
import br.com.spotiflix.models.Bandas;

public class BandaControle {

	Conexao conect = new Conexao();
	Bandas modelo = new Bandas();

	public void salvar(Bandas modelo) {

		conect.conecxao();// conecta com o banco de dados
		try {
			PreparedStatement pst = conect.conecta.prepareStatement("insert into bandas(nome, genero) values(?,?)");

			pst.setString(1, modelo.getNome());
			pst.setString(2, modelo.getGenero());

			pst.execute();
			JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir dados!\n" + ex);
		}

		conect.desconecxao();// desconecta com o banco de dados
	}

	public List<Bandas> read(String desc) {
		conect.conecxao();
		ResultSet rs = null;
		PreparedStatement stmt = null;

		List<Bandas> bandas = new ArrayList<>();

		try {
			stmt = conect.conecta.prepareStatement("SELECT * FROM bandas WHERE data LIKE ?");
			stmt.setString(1, "%" + desc + "%");

			rs = stmt.executeQuery();

			while (rs.next()) {

				Bandas banda = new Bandas();

				banda.setId(rs.getLong("id"));
				banda.setNome(rs.getString("nome"));
				banda.setGenero(rs.getString("genero"));

				bandas.add(banda);

			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao pesquisar!\n" + ex.getMessage());

		}

		conect.desconecxao();
		return bandas;

	}

	public List<Bandas> readAll() {
		conect.conecxao();
		ResultSet rs = null;
		PreparedStatement stmt = null;

		List<Bandas> bandas = new ArrayList<>();

		try {
			stmt = conect.conecta.prepareStatement("SELECT * FROM bandas");

			rs = stmt.executeQuery();

			while (rs.next()) {

				Bandas banda = new Bandas();

				banda.setId(rs.getLong("id"));
				banda.setNome(rs.getString("nome"));
				banda.setGenero(rs.getString("genero"));

				bandas.add(banda);

			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao pesquisar!\n" + ex.getMessage());

		}

		conect.desconecxao();
		return bandas;

	}

	public void update(Bandas modelo) {
		conect.conecxao();

		try {
			PreparedStatement stmt = conect.conecta
					.prepareStatement("UPDATE bandas SET nome = ?, genero = ? WHERE id = ?");

			stmt.setString(1, modelo.getNome());
			stmt.setString(2, modelo.getGenero());

			stmt.execute();

			JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar dados!\n" + ex.getMessage());
		}

		conect.desconecxao();
	}

	public void delete(Bandas modelo) {

		conect.conecxao();

		PreparedStatement stmt = null;

		try {
			stmt = conect.conecta.prepareStatement("DELETE FROM bandas WHERE id = ?");
			stmt.setLong(1, modelo.getId());

			stmt.execute();
			JOptionPane.showMessageDialog(null, "Dados deletado com sucesso!");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao deletar dados!\n" + ex.getMessage());
		}

	}
}
