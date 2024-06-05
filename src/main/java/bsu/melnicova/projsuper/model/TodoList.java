package bsu.melnicova.projsuper.model;

import java.util.Date;

public class TodoList {
	private int id;
	private String description;
	private Date createDate;
	private Date deadline;

	public TodoList() {
		super();
	}

	public TodoList(int id, String description, Date createDate, Date deadline) {
		super();
		this.id = id;
		this.description = description;
		this.createDate = createDate;
		this.deadline = deadline;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@Override
	public String toString() {
		return "TodoList [id=" + id + ", description=" + description + ", createDate=" + createDate
				+ ", deadLine=" + deadline + "]";
	}

}
