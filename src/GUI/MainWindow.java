package GUI;

import Chemistry.Molecule;
import com.nilo.plaf.nimrod.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;

public class MainWindow extends javax.swing.JFrame {

	private static final int maxMolecules = 500;
	private static final int maxTemperature = 1000;
	private static final int maxPressure = 10;
	private CanvasPanel canvas;

	public MainWindow () {
		initComponents ();
		canvas = new CanvasPanel (pCanvas.getWidth (), pCanvas.getHeight (), 60);
		pCanvas.setLayout (new BorderLayout ());
		pCanvas.add (canvas, BorderLayout.CENTER);
		this.concA.setForeground (Molecule.COLOR_A);
		this.concB.setForeground (Molecule.COLOR_B);
		this.concC.setForeground (Molecule.COLOR_C);
		initSliders ();
		initButtons ();
		canvas.addNRandomParticles (sliderConcA.getValue (), Molecule.MOLECULE_A);
		canvas.addNRandomParticles (sliderConcB.getValue (), Molecule.MOLECULE_B);
	}

	@SuppressWarnings ("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pCanvas = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblTemp = new javax.swing.JLabel();
        sliderTemp = new javax.swing.JSlider();
        lblPressure = new javax.swing.JLabel();
        sliderPressure = new javax.swing.JSlider();
        lblSliderConcA = new javax.swing.JLabel();
        sliderConcA = new javax.swing.JSlider();
        lblSliderConcB = new javax.swing.JLabel();
        sliderConcB = new javax.swing.JSlider();
        bStart = new javax.swing.JButton();
        bStop = new javax.swing.JButton();
        lblParameters = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblConcA = new javax.swing.JLabel();
        concA = new javax.swing.JLabel();
        lblConcB = new javax.swing.JLabel();
        concB = new javax.swing.JLabel();
        lblConcC = new javax.swing.JLabel();
        concC = new javax.swing.JLabel();
        lblKe = new javax.swing.JLabel();
        rxnQuotient = new javax.swing.JLabel();
        bReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout pCanvasLayout = new javax.swing.GroupLayout(pCanvas);
        pCanvas.setLayout(pCanvasLayout);
        pCanvasLayout.setHorizontalGroup(
            pCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 912, Short.MAX_VALUE)
        );
        pCanvasLayout.setVerticalGroup(
            pCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblTemp.setText("Temperature (K)");

        lblPressure.setText("Pressure (atm)");

        lblSliderConcA.setText("A Concentration (M)");

        lblSliderConcB.setText("B Concentration (M)");

        bStart.setText("Start");

        bStop.setText("Stop");

        lblParameters.setText("Parameters:");

        lblData.setText("Data:");

        lblConcA.setText("Concentration of A =");

        concA.setText("[A]");

        lblConcB.setText("Concentration of B =");

        concB.setText("[B]");

        lblConcC.setText("Concentration of C =");

        concC.setText("[C]");

        lblKe.setText("Reaction Quotient = ");

        rxnQuotient.setText("{Q}");

        bReset.setText("Reset");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(sliderConcA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sliderConcB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sliderTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sliderPressure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblData))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblParameters))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(bStart, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bStop, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bReset))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSliderConcA)
                            .addComponent(lblSliderConcB)
                            .addComponent(lblTemp)
                            .addComponent(lblPressure, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblKe, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblConcC, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblConcB, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblConcA, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(concA)
                            .addComponent(concB)
                            .addComponent(concC)
                            .addComponent(rxnQuotient))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblParameters)
                .addGap(9, 9, 9)
                .addComponent(lblSliderConcA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sliderConcA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSliderConcB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sliderConcB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTemp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sliderTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPressure)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sliderPressure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConcA)
                    .addComponent(concA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConcB)
                    .addComponent(concB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConcC)
                    .addComponent(concC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKe)
                    .addComponent(rxnQuotient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 247, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bStart)
                    .addComponent(bStop)
                    .addComponent(bReset))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void initSliders () {
		sliderConcA.setMaximum (maxMolecules / 2);
		sliderConcB.setMaximum (maxMolecules / 2);
		sliderTemp.setMaximum (maxTemperature);
		sliderPressure.setMaximum (maxPressure);
		sliderConcA.setValue (sliderConcA.getMaximum () / 2);
		sliderConcB.setValue (sliderConcB.getMaximum () / 2);
		sliderTemp.setValue (298);
		sliderPressure.setValue (1);
	}

	private void initButtons () {
		bStart.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
				canvas.startSimulation ();
			}
		});

		bStop.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
				canvas.stopSimulation ();
			}
		});

		bReset.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
				canvas.stopSimulation ();
				canvas.pEngine.deleteAllParticles ();
				canvas.addNRandomParticles (sliderConcA.getValue (), Molecule.MOLECULE_A);
				canvas.addNRandomParticles (sliderConcB.getValue (), Molecule.MOLECULE_B);
				canvas.repaint ();
				System.out.println (sliderConcA.getValue () + sliderConcB.getValue ());
			}
		});
	}

	public static void main (String args[]) {
		try {
			NimRODTheme nrTheme = new NimRODTheme ("Resources/DarkGrey.theme");
			NimRODLookAndFeel nrLF = new NimRODLookAndFeel ();
			NimRODLookAndFeel.setCurrentTheme (nrTheme);
			UIManager.setLookAndFeel (nrLF);
		}
		catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger (MainWindow.class.getName ()).log (java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater (new Runnable () {
			@Override
			public void run () {
				new MainWindow ().setVisible (true);
			}
		});
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bReset;
    private javax.swing.JButton bStart;
    private javax.swing.JButton bStop;
    private javax.swing.JLabel concA;
    private javax.swing.JLabel concB;
    private javax.swing.JLabel concC;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblConcA;
    private javax.swing.JLabel lblConcB;
    private javax.swing.JLabel lblConcC;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblKe;
    private javax.swing.JLabel lblParameters;
    private javax.swing.JLabel lblPressure;
    private javax.swing.JLabel lblSliderConcA;
    private javax.swing.JLabel lblSliderConcB;
    private javax.swing.JLabel lblTemp;
    private javax.swing.JPanel pCanvas;
    private javax.swing.JLabel rxnQuotient;
    private javax.swing.JSlider sliderConcA;
    private javax.swing.JSlider sliderConcB;
    private javax.swing.JSlider sliderPressure;
    private javax.swing.JSlider sliderTemp;
    // End of variables declaration//GEN-END:variables
}
