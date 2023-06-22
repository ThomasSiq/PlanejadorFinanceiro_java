package dao;

import java.io.IOException; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class PegaDadosDao {
	
		
		public static ArrayList<ArrayList<Object>> getTable(String tabela,String user) throws SQLException {

			ArrayList<ArrayList<Object>> lista = new ArrayList();
			try {

				Connection conn = BancoDados.conectar();

				PreparedStatement st = null;
				ResultSet rs = null;

				try {
					st = conn.prepareStatement("select * from " + tabela +" where user = \""+user+"\"");
					rs = st.executeQuery();

					while (rs.next()) {
						ArrayList<Object> tupla = new ArrayList();
						tupla.add(rs.getInt("codigo"));
						tupla.add(rs.getInt("ano"));
						tupla.add(rs.getInt("mes"));
						tupla.add(rs.getInt("duracao"));
						tupla.add(rs.getString("categoria"));
						tupla.add(rs.getString("nome"));
						tupla.add(rs.getDouble("mensal"));
						tupla.add(rs.getDouble("ocasional"));
						tupla.add(rs.getDouble("total"));

						lista.add(tupla);
					}

				} finally {

					BancoDados.finalizarStatement(st);
					BancoDados.finalizarResultSet(rs);
					BancoDados.desconectar();
				}

			} catch (IOException e) {
				System.out.println(">>>" + e.getMessage());
			}
			return lista;
		}

		public static ArrayList<Integer> getTableYears(String tabela,String user) throws SQLException {

			ArrayList<Integer> anos = new ArrayList();
			try {

				Connection conn = BancoDados.conectar();

				PreparedStatement st = null;
				ResultSet rs = null;

				try {
					st = conn.prepareStatement("select distinct ano from " + tabela + " where user = \""+user+"\" order by ano DESC");
					rs = st.executeQuery();

					while (rs.next()) {

						anos.add(rs.getInt("ano"));
					}

				} finally {

					BancoDados.finalizarStatement(st);
					BancoDados.finalizarResultSet(rs);
					BancoDados.desconectar();
				}

			} catch (IOException e) {
				System.out.println(">>>" + e.getMessage());
			}
			return anos;
		}
		
		public static ArrayList<String> getCategoria(String tabela, String user) throws SQLException{
			
			ArrayList<String> categorias = new ArrayList();
			try {

				Connection conn = BancoDados.conectar();

				PreparedStatement st = null;
				ResultSet rs = null;

				try {
					st = conn.prepareStatement("select * from categoria" + tabela+" where user = \""+user+"\" ");
					rs = st.executeQuery();

					while (rs.next()) {
						
						categorias.add(rs.getString("categoria"));
					}

				} finally {

					BancoDados.finalizarStatement(st);
					BancoDados.finalizarResultSet(rs);
					BancoDados.desconectar();
				}

			} catch (IOException e) {
				System.out.println(">>>" + e.getMessage());
			}
			return categorias;

			
		}
		
		public static String pegaUser(String user) throws SQLException {
			String senha = new String();
			try {
				Connection conn = BancoDados.conectar();
				PreparedStatement st = null;
				ResultSet rs = null;
				

				try {
					st = conn.prepareStatement("select * from userSenhas where user = \"" + user+"\"");
					rs = st.executeQuery();

					while (rs.next()) {
						
						senha = (rs.getString("senha"));
						
					}

				} finally {

					BancoDados.finalizarStatement(st);
					BancoDados.finalizarResultSet(rs);
					BancoDados.desconectar();
				}

			} catch (IOException e) {
				System.out.println(">>>" + e.getMessage());
			}
			return senha;
		}
		public static boolean cadastraUser(String user, String senha) throws SQLException {
			String nome = new String();
			try {
				Connection conn = BancoDados.conectar();
				PreparedStatement st = null;
				ResultSet rs = null;
				

				try {
					st = conn.prepareStatement("select * from userSenhas where user = \"" +user+"\"");
					rs = st.executeQuery();

					while (rs.next()) {
						
						nome = (rs.getString("user"));
						
					}
					if(nome.isEmpty() || !nome.equals(user)) {			
						Statement stt = null;
						String statement;
						statement = "insert into userSenhas (user, senha) values (\""+user.toString()+"\", \""+senha.toString()+"\") ";
						stt = conn.createStatement();
						st.executeUpdate(statement);
					}
				} finally {

					BancoDados.finalizarStatement(st);
					BancoDados.finalizarResultSet(rs);
					BancoDados.desconectar();
				}

			} catch (IOException e) {
				System.out.println(">>>" + e.getMessage());
			}
			
			if(nome.isEmpty() || !nome.equals(user)) {
				return true;
			}
			return false;
		}
		
		public static boolean deletaUser(String user, String senha) throws SQLException {
			String nome = new String();
			try {
				Connection conn = BancoDados.conectar();
				PreparedStatement st = null;
				ResultSet rs = null;
				

				try {
					st = conn.prepareStatement("select * from userSenhas where user = \"" +user+"\"");
					rs = st.executeQuery();

					while (rs.next()) {
						
						nome = (rs.getString("senha"));
						
					}
					if(nome.isEmpty() || !nome.equals(user)) {
						Statement stt = null;
						String statement;
						statement = "delete from userSenhas where user = \"" +user+"\"";
						stt = conn.createStatement();
						st.executeUpdate(statement);
					}
				} finally {

					BancoDados.finalizarStatement(st);
					BancoDados.finalizarResultSet(rs);
					BancoDados.desconectar();
				}

			} catch (IOException e) {
				System.out.println(">>>" + e.getMessage());
			}
			
			if(nome.isEmpty() || !nome.equals(user)) {
				return true;
			}
			return false;
		}
}
