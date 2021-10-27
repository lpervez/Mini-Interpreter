
// TO DO: add your implementation and JavaDocs.
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * This is Interpreter Class.
 * 
 * @author laraibpervez
 *
 */
public class Interpreter {

	/**
	 * phaseOne.
	 */
	private Stack<Integer> phaseOne = new Stack<Integer>();
	/**
	 * Phase2.
	 */
	private HashMap<Integer, Integer> phaseTwo = new HashMap<Integer, Integer>();
	/**
	 * Hash.
	 */
	private HashMap<Integer, Node<Instruction>> phaseTwohash = new HashMap<Integer, Node<Instruction>>();
	// Phase2:
	// create an attribute of type HashMap<Integer, Integer> to map the index of a
	// variable to its value
	// create an attribute of type HashMap<Integer, Node<Instruction>> to map the
	// offset of an instruction to its corresponding node in the LList

	// public static LList<Instruction> readFile(String filename) throws IOException
	//
	// parse the instructions in fileName and store them in a LList<Instruction>
	// (each as a Node<Instruction>)
	// return the resulting LList<Instruction>
	// In the list, the instructions must follow the same order as in the input file
	//
	// (Note: Do not use "dummy nodes" since this is Java and not C)
	/**
	 * Read file.
	 * 
	 * @param filename filename
	 * @return LList
	 * @throws IOException exception
	 */
	public static LList<Instruction> readFile(String filename) throws IOException {
		File file = new File(filename);
		Scanner readfile = new Scanner(file);
		LList<Instruction> output = new LList<Instruction>();

		while (readfile.hasNextLine()) {
			String s = readfile.nextLine();
			Instruction ins = new Instruction(s);

			output.insertLast(new Node<Instruction>(ins));
		}
		readfile.close();

		return output;
	}

	/**
	 * Evaluate.
	 * 
	 * @param list list
	 * @throws IOException exception
	 */

	public void evaluateInstructions(LList<Instruction> list) {

		Node<Instruction> node = list.getFirst();
		while (node != null) {
			phaseTwohash.put(node.getValue().getOffset(), node);
			node = node.getNext();
		}

		node = list.getFirst();

		while (node != null) {

			int value1, value2, result;
			Instruction ins = node.getValue(); // Iterate and get value
			switch (ins.getOpcode()) {
				case "iconst_0":
					phaseOne.push(0);
					node = node.getNext();
					break;
				case "iconst_1":
					phaseOne.push(1);
					node = node.getNext();
					break;
				case "iconst_2":
					phaseOne.push(2);
					node = node.getNext();
					break;
				case "iconst_3":
					phaseOne.push(3);
					node = node.getNext();
					break;
				case "iconst_4":
					phaseOne.push(4);
					node = node.getNext();
					break;
				case "iconst_5":
					phaseOne.push(5);
					node = node.getNext();
					break;
				case "bipush":
					phaseOne.push(ins.getParam1());
					node = node.getNext();
					break;
				case "iadd":
					value1 = phaseOne.pop();
					value2 = phaseOne.pop();
					result = value2 + value1;
					phaseOne.push(result);
					node = node.getNext();
					break;
				case "imul":
					value1 = phaseOne.pop();
					value2 = phaseOne.pop();
					result = value2 * value1;
					phaseOne.push(result);
					node = node.getNext();
					break;

				case "idiv":
					value1 = phaseOne.pop();
					value2 = phaseOne.pop();
					result = value2 / value1;
					phaseOne.push(result);
					node = node.getNext();
					break;

				case "isub":
					value1 = phaseOne.pop();
					value2 = phaseOne.pop();
					result = value2 - value1;
					phaseOne.push(result);
					node = node.getNext();
					break;

				case "irem":
					value1 = phaseOne.pop();
					value2 = phaseOne.pop();
					result = value2 % value1;
					phaseOne.push(result);
					node = node.getNext();
					break;

				case "print":
					value1 = phaseOne.pop();
					System.out.print(value1);
					node = node.getNext();
					if (node != null) {
						System.out.print(" ");
					}
					break;
				case "iload_0":
					phaseOne.push(phaseTwo.get(0));
					node = node.getNext();
					break;
				case "iload_1":
					phaseOne.push(phaseTwo.get(1));
					node = node.getNext();
					break;
				case "iload_2":
					phaseOne.push(phaseTwo.get(2));
					node = node.getNext();
					break;
				case "iload_3":
					phaseOne.push(phaseTwo.get(3));
					node = node.getNext();
					break;
				case "iload":
					phaseOne.push(phaseTwo.get(ins.getParam1()));
					node = node.getNext();
					break;
				case "istore_0":
					value1 = phaseOne.pop();
					phaseTwo.put(0, value1);
					node = node.getNext();
					break;
				case "istore_1":
					value1 = phaseOne.pop();
					phaseTwo.put(1, value1);
					node = node.getNext();
					break;
				case "istore_2":
					value1 = phaseOne.pop();
					phaseTwo.put(2, value1);
					node = node.getNext();
					break;
				case "istore_3":
					value1 = phaseOne.pop();
					phaseTwo.put(3, value1);
					node = node.getNext();
					break;
				case "istore":
					value1 = phaseOne.pop();
					phaseTwo.put(ins.getParam1(), value1);
					node = node.getNext();
					break;
				case "iinc":
					value1 = phaseTwo.get(ins.getParam1());
					phaseTwo.put(ins.getParam1(), value1 + ins.getParam2());
					node = node.getNext();
					break;
				case "goto":
					node = phaseTwohash.get(ins.getParam1());
					break;
				case "if_icmpeg":
					value1 = phaseOne.pop();
					value2 = phaseOne.pop();
					if (value1 == value2) {
						node = phaseTwohash.get(ins.getParam1());
					} else {
						node = node.getNext();
					}
					break;
				case "if_icmpne":
					value1 = phaseOne.pop();
					value2 = phaseOne.pop();
					if (value1 != value2) {
						node = phaseTwohash.get(ins.getParam1());
					} else {
						node = node.getNext();
					}
					break;
				case "if_icmpge":
					value1 = phaseOne.pop();
					value2 = phaseOne.pop();
					if (value2 >= value1) {
						node = phaseTwohash.get(ins.getParam1());
					} else {
						node = node.getNext();
					}
					break;
				case "if_icmpgt":
					value1 = phaseOne.pop();
					value2 = phaseOne.pop();
					if (value2 > value1) {
						node = phaseTwohash.get(ins.getParam1());
					} else {
						node = node.getNext();
					}
					break;
				case "if_icmple":
					value1 = phaseOne.pop();
					value2 = phaseOne.pop();
					if (value2 <= value1) {
						node = phaseTwohash.get(ins.getParam1());
					} else {
						node = node.getNext();
					}
					break;
				case "if_icmplt":
					value1 = phaseOne.pop();
					value2 = phaseOne.pop();
					if (value1 < value2) {
						node = phaseTwohash.get(ins.getParam1());
					} else {
						node = node.getNext();
					}
					break;
				case "ifne":
					value1 = phaseOne.pop();
					if (value1 != 0) {
						node = phaseTwohash.get(ins.getParam1());
					} else {
						node = node.getNext();
					}
					break;
				default:
					node = node.getNext();
			}
		}
		System.out.println("");
	}

	/**
	 * Main Method.
	 * 
	 * @param args args
	 */
	public static void main(String[] args) {

		if (args.length != 1) {
			System.out.println("Usage: java Interpreter [filename]");
			System.exit(0);
		}

		try {
			LList<Instruction> input = readFile(args[0]);
			new Interpreter().evaluateInstructions(input);
		} catch (IOException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}

		// to test the readFile() method, do something similar to below
		/*
		 * try { LList<Instruction> input1 = readFile("Inputs_phase1/Input2.txt"); if
		 * (input1.listToString().equals(
		 * "0: iconst_1  1: iconst_2  2: iadd  3: iconst_3  4: imul  5: iconst_4  6: isub  7: print  10: return "
		 * )) { System.out.println("Yay1"); } } catch (IOException e) {
		 * System.out.println(e.toString()); e.printStackTrace(); }
		 */

	}
}