package br.com.spotiflix.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.spotiflix.conection.Conexao;
import br.com.spotiflix.models.Usuario;

public class UsuarioControle {

	Conexao conect = new Conexao();
	Usuario modelo = new Usuario();

	public void salvar(Usuario modelo) {

		conect.conecxao();// conecta com o banco de dados
		try {
			PreparedStatement pst = conect.conecta.prepareStatement("insert into usuarios(nome, email, senha) values(?,?,?)");

			pst.setString(1, modelo.getNome());
			pst.setString(2, modelo.getEmail());
			pst.setString(3, modelo.getSenha());

			pst.execute();
			JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir dados!\n" + ex);
		}

		conect.desconecxao();// desconecta com o banco de dados
	}

	public List<Usuario> read(String desc) {
		conect.conecxao();
		ResultSet rs = null;
		PreparedStatement stmt = null;

		List<Usuario> usuarios = new ArrayList<>();

		try {
			stmt = conect.conecta.prepareStatement("SELECT id, nome, email FROM usuarios WHERE data LIKE ?");
			stmt.setString(1, "%" + desc + "%");

			rs = stmt.executeQuery();

			while (rs.next()) {

				Usuario usuario = new Usuario();

				usuario.setId(rs.getLong("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));

				usuarios.add(usuario);

			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao pesquisar!\n" + ex.getMessage());

		}

		conect.desconecxao();
		return usuarios;

	}

	public List<Usuario> readAll() {
		conect.conecxao();
		ResultSet rs = null;
		PreparedStatement stmt = null;

		List<Usuario> usuarios = new ArrayList<>();

		try {
			stmt = conect.conecta.prepareStatement("SELECT id, nome, email FROM usuarios");

			rs = stmt.executeQuery();

			while (rs.next()) {

				Usuario usuario = new Usuario();

				usuario.setId(rs.getLong("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));

				usuarios.add(usuario);

			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao pesquisar!\n" + ex.getMessage());

		}

		conect.desconecxao();
		return usuarios;

	}

	public void update(Usuario modelo) {
		conect.conecxao();

		try {
			PreparedStatement stmt = conect.conecta
					.prepareStatement("UPDATE usuarios SET nome = ?, email =?, senha = ? WHERE id = ?");

			stmt.setString(1, modelo.getNome());
			stmt.setString(2, modelo.getEmail());
			stmt.setString(3, modelo.getSenha());
			

			stmt.execute();

			JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar dados!\n" + ex.getMessage());
		}

		conect.desconecxao();
	}

	public void delete(Usuario modelo) {

		conect.conecxao();

		PreparedStatement stmt = null;

		try {
			stmt = conect.conecta.prepareStatement("DELETE FROM usuarios WHERE id = ?");
			stmt.setLong(1, modelo.getId());

			stmt.execute();
			JOptionPane.showMessageDialog(null, "Dados deletado com sucesso!");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao deletar dados!\n" + ex.getMessage());
		}

	}

}
