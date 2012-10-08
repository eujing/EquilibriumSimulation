package GUI;

import Chemistry.Molecule;
import Graph.*;
import com.nilo.plaf.nimrod.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainWindow extends javax.swing.JFrame {

	public static final int maxMolecules = 40000;
	public static final int maxTemperature = 2000;
	public static final int DEFAULT_CONC = maxMolecules / 4;
	public static final int DEFAULT_TEMP = 298;
	private CanvasPanel canvas;
	private GraphPanel graphReactionQuotient;
	private GraphPanel graphConcentrations;
	private boolean firstRun = true;

	public MainWindow () {
		initComponents ();
		initCanvas ();
		initGraph ();
		initSliders ();
		initButtons ();
		this.pack ();
	}

	@SuppressWarnings ("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblTemp = new javax.swing.JLabel();
        sliderTemp = new javax.swing.JSlider();
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
        lblValueConcA = new javax.swing.JLabel();
        lblValueConcB = new javax.swing.JLabel();
        lblValueTemp = new javax.swing.JLabel();
        lblValuePressure = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        pCanvas = new javax.swing.JPanel();
        pGraph = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTemp.setText("Temperature (K):");

        lblSliderConcA.setText("A Concentration (M)");

        lblSliderConcB.setText("B Concentration (M):");

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
                            .addComponent(sliderTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                            .addComponent(lblTemp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblValuePressure, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblValueTemp, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblValueConcB, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblValueConcA, javax.swing.GroupLayout.Alignment.TRAILING)))
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSliderConcA)
                    .addComponent(lblValueConcA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sliderConcA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSliderConcB)
                    .addComponent(lblValueConcB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sliderConcB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTemp)
                    .addComponent(lblValueTemp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sliderTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblValuePressure)
                .addGap(61, 61, 61)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bStart)
                    .addComponent(bStop)
                    .addComponent(bReset))
                .addContainerGap())
        );

        jTabbedPane1.setDoubleBuffered(true);

        javax.swing.GroupLayout pCanvasLayout = new javax.swing.GroupLayout(pCanvas);
        pCanvas.setLayout(pCanvasLayout);
        pCanvasLayout.setHorizontalGroup(
            pCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 907, Short.MAX_VALUE)
        );
        pCanvasLayout.setVerticalGroup(
            pCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 605, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Molecules", jPanel1);

        javax.swing.GroupLayout pGraphLayout = new javax.swing.GroupLayout(pGraph);
        pGraph.setLayout(pGraphLayout);
        pGraphLayout.setHorizontalGroup(
            pGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 907, Short.MAX_VALUE)
        );
        pGraphLayout.setVerticalGroup(
            pGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 605, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Trends", pGraph);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 912, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void initCanvas () {
		canvas = new CanvasPanel (pCanvas.getWidth (), pCanvas.getHeight (), 60);
		pCanvas.setLayout (new BorderLayout ());
		pCanvas.add (canvas, BorderLayout.CENTER);
		this.concA.setForeground (Molecule.COLOR_A);
		this.concB.setForeground (Molecule.COLOR_B);
		this.concC.setForeground (Molecule.COLOR_C);
	}

	private void initGraph () {
		DataSet[] datasetReactionQuotient = {
			new DataSet (Color.BLUE) {
				@Override
				public float getNextDataPoint () {
					return canvas.rEngine.calcReactionQuotient ();
				}
			}
		};
		graphReactionQuotient = new GraphPanel (pGraph.getWidth (), pGraph.getHeight () / 2, 10, 1000, 10, datasetReactionQuotient);
		graphReactionQuotient.setTitle ("Graph of Q against t");
		graphReactionQuotient.setLabels ("t", "Q");
		
		DataSet[] datasetConcentrations = {
			new DataSet (Molecule.COLOR_A) {
				@Override
				public float getNextDataPoint () {
					return canvas.rEngine.getConcOfType (Molecule.MOLECULE_A);
				}
			},
			
			new DataSet (Molecule.COLOR_B) {
				@Override
				public float getNextDataPoint () {
					return canvas.rEngine.getConcOfType (Molecule.MOLECULE_B);
				}
			},
			
			new DataSet (Molecule.COLOR_C) {
				@Override
				public float getNextDataPoint () {
					return canvas.rEngine.getConcOfType (Molecule.MOLECULE_C);
				}
			}
		};
		graphConcentrations = new GraphPanel (pGraph.getWidth (), pGraph.getHeight () / 2, maxMolecules / 1000, 1000, 10, datasetConcentrations);
		graphConcentrations.setTitle ("Graph of species concentrations against t");
		graphConcentrations.setLabels ("t", "[X]");
		
		pGraph.setLayout (new GridLayout (2, 1));
		pGraph.add (graphReactionQuotient);
		pGraph.add (graphConcentrations);
	}

	private void initSliders () {
		sliderConcA.setMinimum (1);
		sliderConcA.setMaximum (maxMolecules / 2);

		sliderConcB.setMinimum (1);
		sliderConcB.setMaximum (maxMolecules / 2);

		sliderTemp.setMinimum (1);
		sliderTemp.setMaximum (maxTemperature);

		sliderConcA.setValue (DEFAULT_CONC);
		lblValueConcA.setText ("" + DEFAULT_CONC);
		sliderConcB.setValue (DEFAULT_CONC);
		lblValueConcB.setText ("" + DEFAULT_CONC);
		sliderTemp.setValue (DEFAULT_TEMP);
		lblValueTemp.setText ("" + DEFAULT_TEMP);

		sliderConcA.addChangeListener (new ChangeListener () {
			@Override
			public void stateChanged (ChangeEvent e) {
				JSlider source = (JSlider) e.getSource ();
				lblValueConcA.setText ("" + source.getValue ());
			}
		});

		sliderConcB.addChangeListener (new ChangeListener () {
			@Override
			public void stateChanged (ChangeEvent e) {
				JSlider source = (JSlider) e.getSource ();
				lblValueConcB.setText ("" + source.getValue ());
			}
		});

		sliderTemp.addChangeListener (new ChangeListener () {
			@Override
			public void stateChanged (ChangeEvent e) {
				JSlider source = (JSlider) e.getSource ();
				lblValueTemp.setText ("" + source.getValue ());
				canvas.rEngine.setTemperature (source.getValue ());
			}
		});
	}

	private void initButtons () {
		bStart.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
				if (firstRun) {
					canvas.addNRandomParticles (sliderConcA.getValue (), sliderTemp.getValue (), Molecule.MOLECULE_A);
					canvas.addNRandomParticles (sliderConcB.getValue (), sliderTemp.getValue (), Molecule.MOLECULE_B);
					firstRun = false;
				}
				canvas.startSimulation ();
				graphReactionQuotient.startPlotting ();
				graphConcentrations.startPlotting ();
			}
		});

		bStop.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
				canvas.stopSimulation ();
				graphReactionQuotient.stopPlotting ();
				graphConcentrations.stopPlotting ();
			}
		});

		bReset.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
				canvas.stopSimulation ();
				graphReactionQuotient.stopPlotting ();
				graphConcentrations.stopPlotting ();
				graphReactionQuotient.reset ();
				graphConcentrations.reset ();
				canvas.rEngine.pEngine.deleteAllParticles ();
				canvas.addNRandomParticles (sliderConcA.getValue (), sliderTemp.getValue (), Molecule.MOLECULE_A);
				canvas.addNRandomParticles (sliderConcB.getValue (), sliderTemp.getValue (), Molecule.MOLECULE_B);
				canvas.repaint ();
				System.out.println (sliderTemp.getValue ());
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblConcA;
    private javax.swing.JLabel lblConcB;
    private javax.swing.JLabel lblConcC;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblKe;
    private javax.swing.JLabel lblParameters;
    private javax.swing.JLabel lblSliderConcA;
    private javax.swing.JLabel lblSliderConcB;
    private javax.swing.JLabel lblTemp;
    private javax.swing.JLabel lblValueConcA;
    private javax.swing.JLabel lblValueConcB;
    private javax.swing.JLabel lblValuePressure;
    private javax.swing.JLabel lblValueTemp;
    private javax.swing.JPanel pCanvas;
    private javax.swing.JPanel pGraph;
    private javax.swing.JLabel rxnQuotient;
    private javax.swing.JSlider sliderConcA;
    private javax.swing.JSlider sliderConcB;
    private javax.swing.JSlider sliderTemp;
    // End of variables declaration//GEN-END:variables
}
