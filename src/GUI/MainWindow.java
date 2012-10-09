package GUI;

import Chemistry.IReactable;
import Chemistry.ReactionManager;
import Graph.*;
import com.nilo.plaf.nimrod.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainWindow extends javax.swing.JFrame {

	public static final int maxMolecules = 40000;
	public static final int minEnthalpy = -50;
	public static final int maxEnthalpy = 50;
	public static final int minEa = 0;
	public static final int maxEa = 50;
	public static final int minTemperature = 1;
	public static final int maxTemperature = 2000;
	public static final int DEFAULT_CONC = maxMolecules / 4;
	public static final Color DEFAULT_COLOR_A = new Color (0, 250, 154);
	public static final Color DEFAULT_COLOR_B = new Color (0, 255, 255);
	public static final Color DEFAULT_COLOR_C = new Color (255, 20, 147);
	public static final int DEFAULT_ENTHALPY = -10;
	public static final int DEFAULT_EA = 10;
	public static final int DEFAULT_TEMP = 298;
	private CanvasPanel canvas;
	private GraphPanel graphReactionQuotient;
	private GraphPanel graphConcentrations;
	private Color colorA = DEFAULT_COLOR_A, colorB = DEFAULT_COLOR_B, colorC = DEFAULT_COLOR_C;
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

        pParams = new javax.swing.JPanel();
        pReactionParam = new javax.swing.JPanel();
        lblSliderConcA = new javax.swing.JLabel();
        sliderConcA = new javax.swing.JSlider();
        bColorA = new javax.swing.JButton();
        lblSliderConcB = new javax.swing.JLabel();
        sliderConcB = new javax.swing.JSlider();
        bColorB = new javax.swing.JButton();
        lblColorA = new javax.swing.JLabel();
        pColorA = new javax.swing.JPanel();
        lblColorB = new javax.swing.JLabel();
        pColorB = new javax.swing.JPanel();
        lblColorC = new javax.swing.JLabel();
        pColorC = new javax.swing.JPanel();
        bColorC = new javax.swing.JButton();
        lblValueConcA = new javax.swing.JLabel();
        lblValueConcB = new javax.swing.JLabel();
        pDynamicParam = new javax.swing.JPanel();
        sliderTemp = new javax.swing.JSlider();
        sliderEnthalpy = new javax.swing.JSlider();
        lblActivationEnergy = new javax.swing.JLabel();
        lblEnthalpyChange = new javax.swing.JLabel();
        sliderEa = new javax.swing.JSlider();
        lblTemp = new javax.swing.JLabel();
        lblValueEnthalpy = new javax.swing.JLabel();
        lblValueEa = new javax.swing.JLabel();
        lblValueTemp = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jPanel1 = new javax.swing.JPanel();
        bStart = new javax.swing.JButton();
        bStop = new javax.swing.JButton();
        bReset = new javax.swing.JButton();
        tpViews = new javax.swing.JTabbedPane();
        pCanvas = new javax.swing.JPanel();
        pGraph = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pReactionParam.setBorder(javax.swing.BorderFactory.createTitledBorder("Reaction Parameters"));

        lblSliderConcA.setText("Initial A Concentration:");

        bColorA.setText("Select");

        lblSliderConcB.setText("Initial B Concentration:");

        bColorB.setText("Select");

        lblColorA.setText("A Color:");

        pColorA.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pColorALayout = new javax.swing.GroupLayout(pColorA);
        pColorA.setLayout(pColorALayout);
        pColorALayout.setHorizontalGroup(
            pColorALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        pColorALayout.setVerticalGroup(
            pColorALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        lblColorB.setText("B Color:");

        pColorB.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pColorBLayout = new javax.swing.GroupLayout(pColorB);
        pColorB.setLayout(pColorBLayout);
        pColorBLayout.setHorizontalGroup(
            pColorBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        pColorBLayout.setVerticalGroup(
            pColorBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        lblColorC.setText("C Color:");

        pColorC.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pColorCLayout = new javax.swing.GroupLayout(pColorC);
        pColorC.setLayout(pColorCLayout);
        pColorCLayout.setHorizontalGroup(
            pColorCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        pColorCLayout.setVerticalGroup(
            pColorCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        bColorC.setText("Select");

        lblValueConcA.setText("[A]");

        lblValueConcB.setText("[B]");

        javax.swing.GroupLayout pReactionParamLayout = new javax.swing.GroupLayout(pReactionParam);
        pReactionParam.setLayout(pReactionParamLayout);
        pReactionParamLayout.setHorizontalGroup(
            pReactionParamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sliderConcB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sliderConcA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pReactionParamLayout.createSequentialGroup()
                .addGroup(pReactionParamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pReactionParamLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pReactionParamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblColorA)
                            .addComponent(lblColorB)
                            .addComponent(lblColorC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pReactionParamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pReactionParamLayout.createSequentialGroup()
                                .addComponent(pColorC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bColorC))
                            .addGroup(pReactionParamLayout.createSequentialGroup()
                                .addComponent(pColorA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bColorA))
                            .addGroup(pReactionParamLayout.createSequentialGroup()
                                .addComponent(pColorB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bColorB))))
                    .addGroup(pReactionParamLayout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(lblValueConcA))
                    .addGroup(pReactionParamLayout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(lblValueConcB))
                    .addGroup(pReactionParamLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblSliderConcA))
                    .addGroup(pReactionParamLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblSliderConcB)))
                .addContainerGap())
        );
        pReactionParamLayout.setVerticalGroup(
            pReactionParamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pReactionParamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pReactionParamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSliderConcA)
                    .addComponent(lblValueConcA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sliderConcA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pReactionParamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(pColorA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bColorA)
                    .addComponent(lblColorA))
                .addGap(18, 18, 18)
                .addGroup(pReactionParamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSliderConcB)
                    .addComponent(lblValueConcB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sliderConcB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pReactionParamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(bColorB)
                    .addComponent(pColorB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblColorB))
                .addGap(18, 18, 18)
                .addGroup(pReactionParamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(bColorC)
                    .addComponent(pColorC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblColorC))
                .addContainerGap())
        );

        pDynamicParam.setBorder(javax.swing.BorderFactory.createTitledBorder("Dynamic Parameters"));

        lblActivationEnergy.setText("<html><p> Forward E<sub>a</sub>:</p></html>");

        lblEnthalpyChange.setText("Enthalpy change:");

        lblTemp.setText("Temperature (K):");

        lblValueEnthalpy.setText("dH");

        lblValueEa.setText("Ea");

        lblValueTemp.setText("T");

        javax.swing.GroupLayout pDynamicParamLayout = new javax.swing.GroupLayout(pDynamicParam);
        pDynamicParam.setLayout(pDynamicParamLayout);
        pDynamicParamLayout.setHorizontalGroup(
            pDynamicParamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDynamicParamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDynamicParamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sliderEnthalpy, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                    .addComponent(sliderEa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sliderTemp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pDynamicParamLayout.createSequentialGroup()
                        .addGroup(pDynamicParamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEnthalpyChange)
                            .addComponent(lblActivationEnergy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTemp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pDynamicParamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblValueEa)
                            .addComponent(lblValueEnthalpy)
                            .addComponent(lblValueTemp))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pDynamicParamLayout.setVerticalGroup(
            pDynamicParamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDynamicParamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDynamicParamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEnthalpyChange)
                    .addComponent(lblValueEnthalpy))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sliderEnthalpy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pDynamicParamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblActivationEnergy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValueEa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sliderEa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pDynamicParamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTemp)
                    .addComponent(lblValueTemp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sliderTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bStart.setText("Start");
        jPanel1.add(bStart);

        bStop.setText("Stop");
        jPanel1.add(bStop);

        bReset.setText("Reset");
        jPanel1.add(bReset);

        javax.swing.GroupLayout pParamsLayout = new javax.swing.GroupLayout(pParams);
        pParams.setLayout(pParamsLayout);
        pParamsLayout.setHorizontalGroup(
            pParamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pParamsLayout.createSequentialGroup()
                .addGroup(pParamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pParamsLayout.createSequentialGroup()
                        .addGroup(pParamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pParamsLayout.createSequentialGroup()
                                .addGap(149, 149, 149)
                                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pParamsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pReactionParam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pParamsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pDynamicParam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pParamsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pParamsLayout.setVerticalGroup(
            pParamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pParamsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pReactionParam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pDynamicParam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tpViews.setDoubleBuffered(true);

        javax.swing.GroupLayout pCanvasLayout = new javax.swing.GroupLayout(pCanvas);
        pCanvas.setLayout(pCanvasLayout);
        pCanvasLayout.setHorizontalGroup(
            pCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1032, Short.MAX_VALUE)
        );
        pCanvasLayout.setVerticalGroup(
            pCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 712, Short.MAX_VALUE)
        );

        tpViews.addTab("Molecules", pCanvas);

        javax.swing.GroupLayout pGraphLayout = new javax.swing.GroupLayout(pGraph);
        pGraph.setLayout(pGraphLayout);
        pGraphLayout.setHorizontalGroup(
            pGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1032, Short.MAX_VALUE)
        );
        pGraphLayout.setVerticalGroup(
            pGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 712, Short.MAX_VALUE)
        );

        tpViews.addTab("Trends", pGraph);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tpViews)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pParams, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pParams, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tpViews)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void initCanvas () {
		canvas = new CanvasPanel (pCanvas.getWidth (), pCanvas.getHeight (), 60, new IReactable () {
			@Override
			public Color matchColor (int moleculeType) {
				switch (moleculeType) {
					case ReactionManager.MOLECULE_A:
						return colorA;
					case ReactionManager.MOLECULE_B:
						return colorB;
					case ReactionManager.MOLECULE_C:
						return colorC;
					default:
						return Color.black;
				}
			}

			@Override
			public float getEnthalpy () {
				return (float) sliderEnthalpy.getValue () / 100f;
			}

			@Override
			public float getActivationEnergy () {
				return (float) sliderEa.getValue () / 100f;
			}
		});
		pCanvas.setLayout (new BorderLayout ());
		pCanvas.add (canvas, BorderLayout.CENTER);
	}

	private void initGraph () {
		DataSet[] datasetReactionQuotient = {
			new DataSet () {
				@Override
				public float getNextDataPoint () {
					return canvas.rEngine.calcReactionQuotient ();
				}
				
				@Override
				public Color getColor () {
					return Color.CYAN;
				}
			}
		};
		
		graphReactionQuotient = new GraphPanel (pGraph.getWidth (), pGraph.getHeight () / 2, 10, 1000, 10, datasetReactionQuotient);
		graphReactionQuotient.setTitle ("Graph of Q against t");
		graphReactionQuotient.setLabels ("t", "Q");

		DataSet[] datasetConcentrations = {
			new DataSet () {
				@Override
				public float getNextDataPoint () {
					return canvas.rEngine.getConcOfType (ReactionManager.MOLECULE_A);
				}
				
				@Override
				public Color getColor () {
					return colorA;
				}
			},
			new DataSet () {
				@Override
				public float getNextDataPoint () {
					return canvas.rEngine.getConcOfType (ReactionManager.MOLECULE_B);
				}
				
				@Override
				public Color getColor () {
					return colorB;
				}
			},
			new DataSet () {
				@Override
				public float getNextDataPoint () {
					return canvas.rEngine.getConcOfType (ReactionManager.MOLECULE_C);
				}
				
				@Override
				public Color getColor () {
					return colorC;
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
		sliderConcA.setValue (DEFAULT_CONC);
		lblValueConcA.setText ("" + sliderConcA.getValue ());

		sliderConcB.setMinimum (1);
		sliderConcB.setMaximum (maxMolecules / 2);
		sliderConcB.setValue (DEFAULT_CONC);
		lblValueConcB.setText ("" + sliderConcB.getValue ());

		sliderEnthalpy.setMinimum (minEnthalpy * 100);
		sliderEnthalpy.setMaximum (maxEnthalpy * 100);
		sliderEnthalpy.setValue (DEFAULT_ENTHALPY * 100);
		lblValueEnthalpy.setText ("" + (sliderEnthalpy.getValue () / 100f));

		sliderEa.setMinimum (minEa * 100);
		sliderEa.setMaximum (maxEa * 100);
		sliderEa.setValue (DEFAULT_EA * 100);
		lblValueEa.setText ("" + (sliderEa.getValue () / 100f));

		sliderTemp.setMinimum (1);
		sliderTemp.setMaximum (maxTemperature);
		sliderTemp.setValue (DEFAULT_TEMP);

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

		sliderEnthalpy.addChangeListener (new ChangeListener () {
			@Override
			public void stateChanged (ChangeEvent e) {
				JSlider source = (JSlider) e.getSource ();
				canvas.rEngine.setEnthalpy ((float) source.getValue () / 100f);
				lblValueEnthalpy.setText ("" + (source.getValue () / 100f));
			}
		});

		sliderEa.addChangeListener (new ChangeListener () {
			@Override
			public void stateChanged (ChangeEvent e) {
				JSlider source = (JSlider) e.getSource ();
				canvas.rEngine.setActivationEnergy ((float) source.getValue () / 100f);
				lblValueEa.setText ("" + (source.getValue () / 100f));
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
		pColorA.setBackground (colorA);
		bColorA.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
				Color newColor = JColorChooser.showDialog (rootPane, "Choose a color", colorA);
				colorA = newColor;
				pColorA.setBackground (colorA);
			}
		});
		
		pColorB.setBackground (colorB);
		bColorB.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
				Color newColor = JColorChooser.showDialog (rootPane, "Choose a color", colorB);
				colorB = newColor;
				pColorB.setBackground (colorB);
			}
		});
		
		pColorC.setBackground (colorC);
		bColorC.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
				Color newColor = JColorChooser.showDialog (rootPane, "Choose a color", colorC);
				colorC = newColor;
				pColorC.setBackground (colorC);
			}
		});

		bStart.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
				if (firstRun) {
					canvas.addNRandomParticles (sliderConcA.getValue (), sliderTemp.getValue (), ReactionManager.MOLECULE_A);
					canvas.addNRandomParticles (sliderConcB.getValue (), sliderTemp.getValue (), ReactionManager.MOLECULE_B);
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
				canvas.addNRandomParticles (sliderConcA.getValue (), sliderTemp.getValue (), ReactionManager.MOLECULE_A);
				canvas.addNRandomParticles (sliderConcB.getValue (), sliderTemp.getValue (), ReactionManager.MOLECULE_B);
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
    private javax.swing.JButton bColorA;
    private javax.swing.JButton bColorB;
    private javax.swing.JButton bColorC;
    private javax.swing.JButton bReset;
    private javax.swing.JButton bStart;
    private javax.swing.JButton bStop;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblActivationEnergy;
    private javax.swing.JLabel lblColorA;
    private javax.swing.JLabel lblColorB;
    private javax.swing.JLabel lblColorC;
    private javax.swing.JLabel lblEnthalpyChange;
    private javax.swing.JLabel lblSliderConcA;
    private javax.swing.JLabel lblSliderConcB;
    private javax.swing.JLabel lblTemp;
    private javax.swing.JLabel lblValueConcA;
    private javax.swing.JLabel lblValueConcB;
    private javax.swing.JLabel lblValueEa;
    private javax.swing.JLabel lblValueEnthalpy;
    private javax.swing.JLabel lblValueTemp;
    private javax.swing.JPanel pCanvas;
    private javax.swing.JPanel pColorA;
    private javax.swing.JPanel pColorB;
    private javax.swing.JPanel pColorC;
    private javax.swing.JPanel pDynamicParam;
    private javax.swing.JPanel pGraph;
    private javax.swing.JPanel pParams;
    private javax.swing.JPanel pReactionParam;
    private javax.swing.JSlider sliderConcA;
    private javax.swing.JSlider sliderConcB;
    private javax.swing.JSlider sliderEa;
    private javax.swing.JSlider sliderEnthalpy;
    private javax.swing.JSlider sliderTemp;
    private javax.swing.JTabbedPane tpViews;
    // End of variables declaration//GEN-END:variables
}
