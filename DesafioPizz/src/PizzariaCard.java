import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PizzariaCard extends JFrame {

	private JLabel lblTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PizzariaCard frame = new PizzariaCard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PizzariaCard() {
		setBounds(100, 100, 490, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JRadioButton rdbtn1 = new JRadioButton("Com borda (+5.00 RS)");
		rdbtn1.setBounds(6, 106, 150, 23);
		getContentPane().add(rdbtn1);

		JRadioButton rdbtn2 = new JRadioButton("Sem borda");
		rdbtn2.setBounds(6, 132, 150, 23);
		getContentPane().add(rdbtn2);

		ButtonGroup bordaGroup = new ButtonGroup();
		bordaGroup.add(rdbtn1);
		bordaGroup.add(rdbtn2);

		JLabel lblPizzaria = new JLabel("PICO DA LARICA");
		lblPizzaria.setBounds(175, 11, 138, 14);
		getContentPane().add(lblPizzaria);

		JCheckBox chckSabor1 = new JCheckBox("Peperoni (+10.00 RS)");
		chckSabor1.setBounds(331, 54, 150, 23);
		getContentPane().add(chckSabor1);

		JCheckBox chckSabor2 = new JCheckBox("Mussarela (+8.00 RS)");
		chckSabor2.setBounds(331, 80, 150, 23);
		getContentPane().add(chckSabor2);

		JCheckBox chckSabor3 = new JCheckBox("Salaminho (+9.00 RS)");
		chckSabor3.setBounds(331, 106, 150, 23);
		getContentPane().add(chckSabor3);

		JCheckBox chckSabor4 = new JCheckBox("Costela (+12.00 RS)");
		chckSabor4.setBounds(331, 132, 150, 23);
		getContentPane().add(chckSabor4);

		JLabel lblSaborr = new JLabel("Escolha os sabores (máx 3)");
		lblSaborr.setBounds(307, 25, 150, 14);
		getContentPane().add(lblSaborr);

		JComboBox<String> comboBox1 = new JComboBox<>();
		comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {"Retirada", "Entrega (+7.00 RS)", "No local"}));
		comboBox1.setBounds(6, 186, 150, 22);
		getContentPane().add(comboBox1);

		JLabel lblretirada = new JLabel("Forma de entrega");
		lblretirada.setBounds(6, 161, 150, 14);
		getContentPane().add(lblretirada);

		JCheckBox chckOpcional1 = new JCheckBox("Azeitonas (+2.00 RS)");
		chckOpcional1.setBounds(6, 220, 150, 23);
		getContentPane().add(chckOpcional1);

		JCheckBox chckOpcional2 = new JCheckBox("Bacon (+3.00 RS)");
		chckOpcional2.setBounds(6, 246, 150, 23);
		getContentPane().add(chckOpcional2);

		JCheckBox chckOpcional3 = new JCheckBox("Ovos (+2.50 RS)");
		chckOpcional3.setBounds(6, 272, 150, 23);
		getContentPane().add(chckOpcional3);

		lblTotal = new JLabel("Total: 0.00 RS");
		lblTotal.setBounds(6, 310, 200, 14);
		getContentPane().add(lblTotal);

		JButton btnCalcular = new JButton("Calcular Total");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularTotal(rdbtn1, comboBox1, chckSabor1, chckSabor2, chckSabor3, chckSabor4, chckOpcional1, chckOpcional2, chckOpcional3);
			}
		});
		btnCalcular.setBounds(210, 306, 150, 23);
		getContentPane().add(btnCalcular);
	}

	protected void calcularTotal(JRadioButton rdbtnBorda, JComboBox<String> comboBox, JCheckBox... checkboxes) {
		double total = 0.0;
		int saboresSelecionados = 0;

		if (rdbtnBorda.isSelected()) {
			total += 5.00;
		}

		
		for (int i = 0; i < checkboxes.length - 3; i++) {
			if (checkboxes[i].isSelected()) {
				saboresSelecionados++;
				if (saboresSelecionados > 3) {
					lblTotal.setText("Você só pode selecionar até 3 sabores!");
					return;
				}
				switch (i) {
				case 0:
					total += 10.00;
					break;
				case 1:
					total += 8.00;
					break;
				case 2:
					total += 9.00;
					break;
				case 3:
					total += 12.00;
					break;
				}
			}
		}

		
		for (int i = checkboxes.length - 3; i < checkboxes.length; i++) {
			if (checkboxes[i].isSelected()) {
				switch (i) {
				case 4:
					total += 2.00;
					break;
				case 5:
					total += 3.00;
					break;
				case 6:
					total += 2.50;
					break;
				}
			}
		}

		
		String entregaSelecionada = (String) comboBox.getSelectedItem();
		if (entregaSelecionada != null && entregaSelecionada.contains("Entrega")) {
			total += 7.00;
		}

		lblTotal.setText("Total: " + String.format("%.2f", total) + " RS");
	}
}
