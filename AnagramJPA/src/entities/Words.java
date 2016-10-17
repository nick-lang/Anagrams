package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Words {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String word;
	
	private Double hash;
	
	private int count;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Double getHash() {
		return hash;
	}

	public void setHash(Double hash) {
		this.hash = hash;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Words [id=" + id + ", word=" + word + ", hash=" + hash + ", count=" + count + "]";
	}

}
