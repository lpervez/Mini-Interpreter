
// TO DO: JavaDocs
import java.util.*;

/**
 * Instruction.
 * 
 * @author laraibpervez
 *
 */
public class Instruction {
	/**
	 * off set.
	 */
	private int offset;
	/**
	 * opcode.
	 */
	private String opcode;
	/**
	 * Array list.
	 */
	private ArrayList<Integer> parameters;

	/**
	 * Instruction.
	 * 
	 * @param s s
	 */
	public Instruction(String s) {

		s = s.trim();
		parameters = new ArrayList<Integer>();

		String[] tokens = s.split("[:|,|\\s]+");
		int count = 1;
		for (String token : tokens) {
			String item = token.trim();
			if (item.length() == 0) {
				System.out.println("blank item");
				continue;
			}
			if (count == 1) {
				offset = Integer.valueOf(item);
			} else if (count == 2) {
				opcode = item;
			} else if (count == 3) {
				parameters.add(Integer.valueOf(item));
			} else if (count == 4) {
				parameters.add(Integer.valueOf(item));
			} else {
				throw new RuntimeException("Illegal format: " + item);
			}
			count++;
		}

	}

	/**
	 * To string.
	 * 
	 * @return String
	 */
	public String toString() {
		String s = offset + ": " + opcode + " ";
		if (parameters != null) {
			for (int param : parameters) {
				s += param + " ";
			}
		}
		return s;
	}

	/**
	 * offset.
	 * 
	 * @return int
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * get op code.
	 * 
	 * @return String
	 */
	public String getOpcode() {
		return opcode;
	}

	/**
	 * parameter.
	 * 
	 * @return integer
	 */
	public int getNumParameters() {
		return parameters.size();
	}

	/**
	 * get parameter 1.
	 * 
	 * @return integer
	 */
	public int getParam1() {
		if (getNumParameters() < 1) {
			throw new RuntimeException("instruction takes zero parameters");
		}
		return parameters.get(0);
	}

	/**
	 * get parameter 2.
	 * 
	 * @return integer
	 */
	public int getParam2() {
		if (getNumParameters() < 2) {
			throw new RuntimeException("instruction takes zero or one parameters");
		}
		return parameters.get(1);
	}

	/**
	 * Main Method.
	 * 
	 * @param args args
	 */
	public static void main(String args[]) {

		Instruction ins = new Instruction("0: iconst_2");
		if ((ins.getOffset() == 0) && (ins.getOpcode().equals("iconst_2")) && (ins.getNumParameters() == 0)) {
			System.out.println("Yay1");
		}

		ins = new Instruction("21 : bipush         6");
		if ((ins.getOffset() == 21) && (ins.getOpcode().equals("bipush")) && (ins.getNumParameters() == 1)
				&& (ins.getParam1() == 6)) {
			System.out.println("Yay2");
		}

		ins = new Instruction("40:iinc 4, 1");
		if ((ins.getOffset() == 40) && (ins.getOpcode().equals("iinc")) && (ins.getNumParameters() == 2)
				&& (ins.getParam1() == 4) && (ins.getParam2() == 1)) {
			System.out.println("Yay3");
		}
	}

}