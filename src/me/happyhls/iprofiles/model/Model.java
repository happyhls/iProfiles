package me.happyhls.iprofiles.model;

/**
 * the basic model of iprofiles program,including id,name
 * @author happyhls
 *
 */
public class Model {

	/**
	 * used in database
	 */
	private long id;
	
	/**
	 * the name described the model
	 */
	private String name;

	/**
	 * constructor with id,used in database associated
	 * @param id:different with each other within the same model
	 * @param name
	 */
	public Model(long id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * constructor without id,used before added in database
	 * @param id:different with each other within the same model
	 * @param name
	 */
	public Model(String name){
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	
}
