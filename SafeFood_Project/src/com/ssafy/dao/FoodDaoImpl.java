package com.ssafy.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ssafy.vo.Food;

public class FoodDaoImpl implements FoodDao {

	private static Connection conn;
	private static FoodDaoImpl instance;
	private String[] allergys = { "대두", "땅콩", "우유", "게", "새우", "참치", "연어", "쑥", "소고기", "닭고기", "돼지고기", "복숭아", "민들레",
			"계란흰자" };

	private FoodDaoImpl() {
		conn = getConn();
	}

	public static FoodDaoImpl getInstance() {
		if (instance == null) {
			instance = new FoodDaoImpl();
		}
		return instance;
	}
//	private List<Food> foods;
//	public FoodDaoImpl() {
//		foods=new ArrayList<Food>();
//	}

	public static Connection getConn() {
		if (conn == null) {
			try {
				// 웹 어플리케이션에서는 클래스 네임 적재 생략 불가
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();// 드라이버 적재

				String dbName = "safefood";
				String url = "jdbc:mysql://127.0.0.1/" + dbName
						+ "?allowMultiQueries=true&characterEncoding=UTF-8&serverTimezone=UTC";
				String user = "root";
				String password = "tiger";
				conn = DriverManager.getConnection(url, user, password);

				System.out.println(!conn.isClosed() + " : DB 연결 성공여부");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	public void loadData() {
		try {
			DocumentBuilder builder;
			DocumentBuilderFactory factory;
			Document document;

			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			document = builder.parse(new File(
					"C:\\SSAFY\\work_java_third\\work_java_third\\SafeFood_Web_Back_End_Seoul_6_LeeJungyeon_KimJieun\\WebContent\\xml\\foodInfo.xml"));
			document.getDocumentElement().normalize();

			NodeList nodeList = document.getElementsByTagName("food");
			for (int i = 0; i < nodeList.getLength(); i++) {

				Node foodNode = nodeList.item(i);
				Element foodElement = (Element) foodNode;

				int code = Integer.parseInt(foodElement.getElementsByTagName("code").item(0).getTextContent());
				String name = foodElement.getElementsByTagName("name").item(0).getTextContent();
				String company = foodElement.getElementsByTagName("maker").item(0).getTextContent();
				String materials = foodElement.getElementsByTagName("material").item(0).getTextContent();

				insert(code, name, company, materials);
				for (int j = 0; j < allergys.length; j++) {
					if (materials.contains(allergys[j])) {
						insertAlg(code, allergys[j]);
					}

				}

			}
			System.out.println("loaded!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void insertAlg(int code, String allergy) {		
		String sql = "INSERT INTO foodAlg VALUES(?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			pstmt.setString(2, allergy);
			pstmt.executeUpdate(); // 식품 알러지 삽입
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	}

	private void insert(int code, String name, String company, String materials) {
		String sql = "INSERT INTO food VALUES(?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			pstmt.setString(2, name);
			pstmt.setString(3, company);
			pstmt.setString(4, materials);
			pstmt.executeUpdate(); // 삽입
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 사용한 자원을 반환, finally 실행 후 리턴
			close(pstmt);
		}
	}

	public List<Food> searchAll() {
		List<Food> list = new ArrayList<>();
		String sql = "SELECT * FROM food";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Food food = new Food();
				food.setCode(rs.getInt(1));
				food.setName(rs.getString(2));
				food.setCompany(rs.getString(3));
				food.setMaterials(rs.getString(4));
				list.add(food);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 사용한 자원을 반환, finally 실행 후 리턴
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public List<Integer> searchIntakeCnt(String id) {
		List<Integer> list = new ArrayList<>();
		String sql = "SELECT count FROM intake where id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 사용한 자원을 반환, finally 실행 후 리턴
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public void intakeDelete(String id, int code) {
		String sql = "delete FROM intake where id=? and code=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, code);
			pstmt.executeUpdate();
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				list.add(rs.getInt(1));
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 사용한 자원을 반환, finally 실행 후 리턴
			close(rs);
			close(pstmt);
		}
	};
	
	public List<Food> searchIntake(String id) {
		List<Food> list = new ArrayList<>();
		String sql = "SELECT * FROM food where code = any((SELECT code FROM intake where ID=?))";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Food food = new Food();
				food.setCode(rs.getInt(1));
				food.setName(rs.getString(2));
				food.setCompany(rs.getString(3));
				food.setMaterials(rs.getString(4));
				list.add(food);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 사용한 자원을 반환, finally 실행 후 리턴
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public Food search(int code) {
		Food f = new Food();
		String sql = "SELECT * FROM food LEFT JOIN foodalg ON food.code = foodalg.code WHERE food.code=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			rs.last();
			int size = rs.getRow();
			System.out.println(size);
			String[] allergys = new String[size];
			rs.beforeFirst();
			while (rs.next()) {
				if (rs.getRow() == 1) {
					f.setCode(rs.getInt(1));
					f.setName(rs.getString(2));
					f.setCompany(rs.getString(3));
					f.setMaterials(rs.getString(4));
				}
				// 알러지 조회
//				System.out.println(rs.getRow());
				allergys[rs.getRow() - 1] = rs.getString(6);
			}
			f.setAllergys(allergys);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 사용한 자원을 반환, finally 실행 후 리턴
			close(rs);
			close(pstmt);
		}
		return f;
	}

	public List<Food> search(String name) {
		if (name.equals(""))
			return null;
		List<Food> list = new ArrayList<>();
		String sql = "SELECT * FROM food WHERE name like ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Food food = new Food();
				food.setCode(rs.getInt(1));
				food.setName(rs.getString(2));
				food.setCompany(rs.getString(3));
				food.setMaterials(rs.getString(4));
				list.add(food);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 사용한 자원을 반환, finally 실행 후 리턴
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public List<Food> searchBest() {
		return null;
	}

	public List<Food> searchBestIndex() {
		return null;
	}
	
	

	public static void main(String[] args) {

	}

	public static void print(List<Food> foods) {
		for (Food f : foods) {
			System.out.println(f.toString());
		}
	}

	// close
	private void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
}
