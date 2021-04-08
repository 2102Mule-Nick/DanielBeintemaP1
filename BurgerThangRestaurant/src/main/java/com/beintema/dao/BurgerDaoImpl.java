package com.beintema.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.beintema.dao.mapper.BurgerRowMapper;
import com.beintema.pojo.Burger;

@Repository
public class BurgerDaoImpl implements BurgerDao{
	
	private JdbcTemplate jdbcTemplate;
	
	private BurgerRowMapper burgerRowMapper;
	
	private RowMapper<Integer> rowMapper;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Autowired
	public void setBurgerRowMapper(BurgerRowMapper burgerRowMapper) {
		this.burgerRowMapper = burgerRowMapper;
	}
	
	@Autowired
	public void setRowMapper (RowMapper<Integer> rowMapper) {
		this.rowMapper=rowMapper;
	}

	@Override
	public Burger createBurger(Burger burger) {
		// TODO Auto-generated method stub
		
		String sql="INSERT INTO burgers(burger_name, restaurant_id, ingredients, price, preptime)"
				+ "VALUES('?', ?, '?', ?, ?";
		
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(connection->{
			PreparedStatement ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, burger.getBurgername());
			ps.setInt(2, burger.getRestaurant().getRestaurant_id());
			ps.setString(3, burger.getIngredients());
			ps.setDouble(4, burger.getPrice());
			ps.setInt(5, burger.getPreptime());
			return ps;
		}, keyHolder);
				
		burger.setBurger_id((int) keyHolder.getKeys().get("burger_id"));
//				create table burgers(
//						burger_id serial primary key,
//						burger_name varchar(100),
//						restaurant_id int references restaurant(restaurant_id),
//						ingredients varchar(100),
//						price money,
//						preptime int
//					);
		return burger;
	}

	@Override
	public Burger getById(int burger_id) {
		String sql="SELECT * FROM burgers WHERE burger_id=?";
		
		List<Burger> burgerList=jdbcTemplate.query(sql, burgerRowMapper, burger_id);
		
		return burgerList.get(0);
	}

	@Override
	public List<Burger> getAllBurgers() {
		String sql="SELECT * FROM burgers";
		
		List<Burger> burgerList=jdbcTemplate.query(sql, burgerRowMapper);
		
		return burgerList;
	}

	@Override
	public List<Burger> getBurgerByName(String burger_name) {
		String sql="SELECT * FROM burgers WHERE burger_name='?'";
		
		List<Burger> burgerList=jdbcTemplate.query(sql, burgerRowMapper, burger_name);
		
		return burgerList;
	}

	@Override
	public List<Burger> getBurgerByRestaurant(int restaurant_id) {
		String sql="SELECT * FROM burgers WHERE restaurant_id=?";
		
		List<Burger> burgerList=jdbcTemplate.query(sql, burgerRowMapper, restaurant_id);
		
		return burgerList;
	}

	@Override
	public String removeBurger(Burger burger) {

		String sql="DELETE FROM burgers WHERE burger_id='"+burger.getBurger_id();
		
		jdbcTemplate.execute(sql);
		
		return "Burgers with name "+burger.getBurgername()+" removed";
	}
	
	@Override
	public List<Burger> getBurgerByOrder(int order_id){
		
		String sql="SELECT burger_id FROM burgerorders WHERE order_id=?";
		
		List<Integer> burger_id =jdbcTemplate.query(sql,rowMapper,order_id);
		
		List<Burger> burgers = new ArrayList<Burger>();
		
		for(int i : burger_id) {
			
			Burger newBurger=getById(i);
			
			burgers.add(newBurger);
		}
		return burgers;
	}

	@Override
	public Burger updateBurger(Burger burger) {
		String sql="UPDATE "
				+ "SET burger_name='?',"
				+ "restaurant_id=?,"
				+ "ingredients='?', "
				+ "price=?"
				+ "preptime=?"
				+ "WHERE burger_id=?";
		
//		UPDATE table_name
//		SET column1 = value1, column2 = value2, ...
//		WHERE condition;
		
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(connection->{
			PreparedStatement ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, burger.getBurgername());
			ps.setInt(2, burger.getRestaurant().getRestaurant_id());
			ps.setString(3, burger.getIngredients());
			ps.setDouble(4, burger.getPrice());
			ps.setInt(5, burger.getPreptime());
			ps.setInt(6, burger.getBurger_id());
			return ps;
		}, keyHolder);
				
		return burger;
	}

}
