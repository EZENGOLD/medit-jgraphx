package ui;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import models.Automata;
import models.BooleanVariable;
import models.ClockVariable;
import models.IntVariable;

public class VariablesTree extends JPanel {
	private Automata automata;

	private JTree tree;

	public VariablesTree(Automata automata) {
		this.automata = automata;
		setBorder(new CompoundBorder(BorderFactory.createTitledBorder(" Variables "), new EmptyBorder(0, 5, 5, 5)));
		buildTree();
	}

	public void buildTree() {
		removeAll();

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Déclarations");

		// add clock variables branch
		DefaultMutableTreeNode clocksBranch = new DefaultMutableTreeNode("Horloges");

		if (automata != null && automata.getClockVariablesList() != null) {
			for (String key : this.automata.getClockVariablesList().keySet()) {
				ClockVariable variable = (ClockVariable) this.automata.getClockVariablesList().get(key);

				if (variable != null)
					clocksBranch.add(new DefaultMutableTreeNode(variable.getName() + " = " + variable.getValue()));
			}
		}
		root.add(clocksBranch);

		// add int variables branch
		DefaultMutableTreeNode integersBranch = new DefaultMutableTreeNode("Entiers");

		if (automata != null && automata.getIntVariablesList() != null) {
			for (String key : this.automata.getIntVariablesList().keySet()) {
				IntVariable variable = (IntVariable) this.automata.getIntVariablesList().get(key);

				if (variable != null)
					integersBranch.add(new DefaultMutableTreeNode(variable.getName() + " = " + variable.getValue()));
			}
		}
		root.add(integersBranch);

		// add boolean variables branch
		DefaultMutableTreeNode booleansBranch = new DefaultMutableTreeNode("Booléens");

		if (automata != null && automata.getBoolVariablesList() != null) {
			for (String key : this.automata.getBoolVariablesList().keySet()) {
				BooleanVariable variable = (BooleanVariable) this.automata.getBoolVariablesList().get(key);

				if (variable != null)
					booleansBranch.add(new DefaultMutableTreeNode(variable.getName() + " = " + variable.getValue()));
			}
		}
		root.add(booleansBranch);

		this.tree = new JTree(root);
		tree.setBorder(new EmptyBorder(5, 10, 10, 10));
		tree.setExpandsSelectedPaths(true);

		// remove folder and file icon on tree
		DefaultTreeCellRenderer cellRenderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
		cellRenderer.setLeafIcon(null);
		cellRenderer.setClosedIcon(null);
		cellRenderer.setOpenIcon(null);
		cellRenderer.setDisabledIcon(null);

		setLayout(new BorderLayout());
		add(tree, BorderLayout.CENTER);
		revalidate();
		repaint();

		expandAllTree();
	}

	private void expandAllTree() {
		if (tree != null) {
			for (int i = 0; i < tree.getRowCount(); i++) {
				tree.expandRow(i);
			}
		}
	}

	public void recreateTree() {
		buildTree();
	}

	public Automata getAutomata() {
		return automata;
	}

	public void setAutomata(Automata automata) {
		this.automata = automata;
	}

	public JTree getTree() {
		return tree;
	}

	public void setTree(JTree tree) {
		this.tree = tree;
	}
}
