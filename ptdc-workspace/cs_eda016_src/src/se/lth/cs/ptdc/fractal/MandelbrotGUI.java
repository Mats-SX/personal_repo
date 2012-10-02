package se.lth.cs.ptdc.fractal;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;

/**
 * The graphical user interface for the Mandelbrot assignment.
 */
public class MandelbrotGUI {

	private static final String WINDOW_TITLE = "FractalGenerator";

	private static final int NOOP = 0;

	/**
	 * Render command.
	 * <p>
	 * This constant is returned by <code>getCommand()</code> when the user
	 * presses the <b>RENDER</b> button.
	 * 
	 * @see #getCommand()
	 */
	public static final int RENDER = 1 << 0;

	/**
	 * Zoom command.
	 * <p>
	 * This constant is returned by <code>getCommand()</code> when the user
	 * zooms in the picture.
	 * 
	 * @see #getCommand()
	 */
	public static final int ZOOM = 1 << 1;

	/**
	 * Reset command.
	 * <p>
	 * This constant is returned by <code>getCommand()</code> when the user
	 * presses the <b>RESET</b> button.
	 * 
	 * @see #getCommand()
	 */
	public static final int RESET = 1 << 3;

	/**
	 * Quit command.
	 * <p>
	 * This constant is returned by <code>getCommand()</code> when the user
	 * presses the <b>QUIT</b> button or chooses <b>Quit</b> in the File menu.
	 * 
	 * @see #getCommand()
	 */
	public static final int QUIT = 1 << 4;

	/**
	 * Black and White mode.
	 * <p>
	 * This is the value returned by <code>getMode()</code> when the user has
	 * requested a black and white image.
	 */
	public static final int MODE_BW = 1 << 5;

	/**
	 * Color mode.
	 * <p>
	 * This is the value returned by <code>getMode()</code> when the user has
	 * requested a color image.
	 */
	public static final int MODE_COLOR = 1 << 6;

	/**
	 * Very low resolution.
	 * <p>
	 * This is the value returned by <code>getResolution()</code> when the
	 * user has requested <b>very low</b> resolution of the image.
	 * 
	 * @see #getResolution()
	 */
	public static final int RESOLUTION_VERY_LOW = 1 << 7;

	/**
	 * Low resolution.
	 * <p>
	 * This is the value returned by <code>getResolution()</code> when the
	 * user has requested <b>low</b> resolution of the image.
	 * 
	 * @see #getResolution()
	 */
	public static final int RESOLUTION_LOW = 1 << 8;

	/**
	 * Medium resolution.
	 * <p>
	 * This is the value returned by <code>getResolution()</code> when the
	 * user has requested <b>medium</b> resolution of the image.
	 * 
	 * @see #getResolution()
	 */
	public static final int RESOLUTION_MEDIUM = 1 << 9;

	/**
	 * High resolution.
	 * <p>
	 * This is the value returned by <code>getResolution()</code> when the
	 * user has requested <b>high</b> resolution of the image.
	 * 
	 * @see #getResolution()
	 */
	public static final int RESOLUTION_HIGH = 1 << 10;

	/**
	 * Very high resolution.
	 * <p>
	 * This is the value returned by <code>getResolution()</code> when the
	 * user has requested <b>very high</b> resolution of the image.
	 * 
	 * @see #getResolution()
	 */
	public static final int RESOLUTION_VERY_HIGH = 1 << 11;

	private static final double DEFAULT_MIN_REAL = -2.4;
	private static final double DEFAULT_MAX_REAL = 0.8;
	private static final double DEFAULT_MIN_IMAG = -1.4;
	private static final double DEFAULT_MAX_IMAG = 1.4;
	private static final int DEFAULT_WIDTH = 630;
	private static final int DEFAULT_HEIGHT = 550;

	private int prefWidth;
	private int prefHeight;
	private FGFrame frame;
	private FileHandler fileHandler;

	/**
	 * Creates a graphical user interface.
	 */
	public MandelbrotGUI() {
		minReal = DEFAULT_MIN_REAL;
		maxReal = DEFAULT_MAX_REAL;
		minImag = DEFAULT_MIN_IMAG;
		maxImag = DEFAULT_MAX_IMAG;
		prefWidth = DEFAULT_WIDTH;
		prefHeight = DEFAULT_HEIGHT;
		resolution = RESOLUTION_VERY_HIGH;
		mode = MODE_BW;
		command = NOOP;

		fileHandler = new FileHandler();
		frame = new FGFrame();
		frame.setVisible(true);
	}

	/**
	 * Disables user input.
	 * 
	 * @see #enableInput()
	 */
	public void disableInput() {
		frame.enableComponents(false);
	}

	/**
	 * Enables user input.
	 * 
	 * @see #disableInput()
	 */
	public void enableInput() {
		frame.enableComponents(true);
	}

	/**
	 * Returns the minimum real value.
	 * 
	 * @return Minimum real value of the complex coordinate system.
	 * @see #getMaximumReal()
	 * @see #getMinimumImag()
	 * @see #getMaximumImag()
	 */
	public double getMinimumReal() {
		return minReal;
	}

	private double minReal;

	/**
	 * Returns the maximum real value.
	 * 
	 * @return Maximum real value of the complex coordinate system.
	 * @see #getMinimumReal()
	 * @see #getMinimumImag()
	 * @see #getMaximumImag()
	 */
	public double getMaximumReal() {
		return maxReal;
	}

	private double maxReal;

	/**
	 * Returns the minimum imaginary value.
	 * 
	 * @return Minimum imaginary value of the complex coordinate system.
	 * @see #getMinimumReal()
	 * @see #getMaximumReal()
	 * @see #getMinimumImag()
	 */
	public double getMinimumImag() {
		return minImag;
	}

	private double minImag;

	/**
	 * Returns the maximum imaginary value.
	 * 
	 * @return Maximum imaginary value of the complex coordinate system.
	 * @see #getMinimumReal()
	 * @see #getMaximumReal()
	 * @see #getMinimumImag()
	 */
	public double getMaximumImag() {
		return maxImag;
	}

	private double maxImag;

	/**
	 * Returns the image mode.
	 * <p>
	 * The mode is chosen among the two alternatives <code>MODE_BW</code> and
	 * <code>MODE_COLOR</code>, given as constants by the class.
	 * 
	 * @return The image mode chosen by the user.
	 * @see #MODE_BW
	 * @see #MODE_COLOR
	 */
	public int getMode() {
		return mode;
	}

	private int mode;

	/**
	 * Returns the image resolution.
	 * <p>
	 * The resolution is chosen among five alternatives given as constants in
	 * the class.
	 * 
	 * @return The image resolution chosen by the user.
	 * @see #RESOLUTION_VERY_LOW
	 * @see #RESOLUTION_LOW
	 * @see #RESOLUTION_MEDIUM
	 * @see #RESOLUTION_HIGH
	 * @see #RESOLUTION_VERY_HIGH
	 */
	public int getResolution() {
		return resolution;
	}

	private int resolution;

	/**
	 * Returns the image width.
	 * 
	 * @return The width of the image in pixels.
	 * @see #getHeight
	 */
	public int getWidth() {
		return frame.plane.getWidth();
	}

	/**
	 * Returns the image height.
	 * 
	 * @return The height of the image in pixels.
	 * @see #getWidth
	 */
	public int getHeight() {
		return frame.plane.getHeight();
	}

	/**
	 * Returns a command triggered by the user.
	 * <p>
	 * The possible return values are defined as constants in the class.
	 * 
	 * @return A command triggered by the user.
	 * @see #QUIT
	 * @see #RENDER
	 * @see #ZOOM
	 * @see #RESET
	 */
	public synchronized int getCommand() {
		while (command == NOOP) {
			try {
				wait();
			} catch (InterruptedException ie) {
				/*
				 * FALL THROUGH
				 */
			}
		}
		int c = command;
		command = NOOP;
		return c;
	}

	private synchronized void signal(int command) {
		this.command = command;
		notifyAll();
	}

	private int command;

	/**
	 * Clear the image plane.
	 */
	public void clearPlane() {
		fileHandler.filename = "[noname]";
		frame.clear();
	}

	/**
	 * Resets the axis limits to their default values.
	 */
	public void resetPlane() {
		minReal = DEFAULT_MIN_REAL;
		maxReal = DEFAULT_MAX_REAL;
		minImag = DEFAULT_MIN_IMAG;
		maxImag = DEFAULT_MAX_IMAG;
		frame.setDimensions();
	}

	/**
	 * Draws an image on the complex plane.
	 * <p>
	 * An image represented as a matrix of colors is drawn on the complex plane
	 * of the interface. Matrix element <code>[0][0]</code> is drawn at image
	 * position <code>(0,0)</code>.
	 * <p>
	 * This method is typically used to draw the whole plane in one operation.
	 * 
	 * @param image
	 *            A matrix of color objects, each of whose elements is
	 *            interpreted as a picture element of the image to draw.
	 * @param pixelWidth
	 *            The <b>width</b> of a picture element.
	 * @param pixelHeight
	 *            The <b>height</b> of a picture element.
	 * @see #putData(int,int,Color[][],int,int)
	 */
	public void putData(Color[][] image, int pixelWidth, int pixelHeight) {
		putData(0, 0, image, pixelWidth, pixelHeight);
	}

	/**
	 * Draw an image on the complex plane.
	 * <p>
	 * An image represented as a matrix of colors is drawn on the complex plane
	 * of the interface. Matrix element <code>[0][0]</code> is drawn at image
	 * position <code>(x,y)</code>.
	 * <p>
	 * This method is typically used to draw <em>a part of</em> the image.
	 * 
	 * @param x
	 *            The x-position of matrix element <code>[0][0]</code>
	 * @param y
	 *            The y-position of matrix element <code>[0][0]</code>
	 * @param image
	 *            A matrix of color objects, each of whose elements is
	 *            interpreted as a picture element of the image to draw.
	 * @param pixelWidth
	 *            The <b>width</b> of a picture element.
	 * @param pixelHeight
	 *            The <b>height</b> of a picture element.
	 * @see #putData(Color[][],int,int)
	 */
	public void putData(int x, int y, Color[][] image, int pixelWidth,
			int pixelHeight) {
		frame.plane.putData(x, y, image, pixelWidth, pixelHeight);
	}

	/*
	 * ===========================================================================
	 * ===========================================================================
	 * I N N E R C L A S S E S
	 * ===========================================================================
	 * ===========================================================================
	 */

	/*
	 * Handles image loading and saving functionality.
	 */
	class FileHandler {
		String filename;

		FileHandler() {
			filename = "[noname]";
		}

		void save() {
			/*
			 * If image is not yet saved, it needs a filename. Try saveAs!
			 */
			if (filename.equals("[noname]")) {
				saveAs();
				return;
			}

			File file = new File(filename);
			frame.setStatus("Saving image as " + file.getName());

			/*
			 * Try to write the image to the file system. Beware of errors!
			 */
			try {
				ImageIO.write(frame.backImage, "jpeg", file); // throws
			} catch (java.io.IOException e) {
				JOptionPane.showMessageDialog(frame, "Error writing file.",
						"Microsoft behaviour", JOptionPane.ERROR_MESSAGE);
			}
			frame.setStatus(filename);
		}

		void saveAs() {
			File file = null;
			/*
			 * Get filename from dialog utility.
			 */
			JFileChooser fc = new JFileChooser();
			fc.addChoosableFileFilter(new IFilter());
			int ret = fc.showSaveDialog(frame);
			if (ret == JFileChooser.APPROVE_OPTION) {
				file = fc.getSelectedFile();
			}

			if (file != null) {
				/*
				 * Check that the filename has a JPEG extension. If not, concat!
				 */
				String ext = getExtension(file);
				if (ext == null || !ext.equals("jpg") && !ext.equals("jpeg")) {
					file = new File(file.getPath() + ".jpeg");
				}

				/*
				 * What if file already exists? Ask user!
				 */
				if (file.exists()) {
					int n = JOptionPane.showConfirmDialog(frame, "File "
							+ file.getName() + " already exists.\nOverwrite?",
							"Overwrite existing file?",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (n == JOptionPane.NO_OPTION
							|| n != JOptionPane.YES_OPTION) {
						return;
					}
				}
				frame.setStatus("Saving image as " + file.getName());

				/*
				 * Now, try writing image to file system. Beware of errors!
				 */
				try {
					ImageIO.write(frame.backImage, "jpeg", file); // throws
					filename = file.getPath();
				} catch (java.io.IOException e) {
					JOptionPane.showMessageDialog(frame, "Error writing file.",
							"Microsoft behaviour", JOptionPane.ERROR_MESSAGE);
				}
				frame.setStatus(filename);
			}
		}

		/*
		 * Get filename extension (the part to the right of the last '.')
		 * 
		 * Return null if filename has no extension.
		 */
		String getExtension(File f) {
			String ext = null;
			String s = f.getName();
			int i = s.lastIndexOf('.');
			if (i > 0 && i < s.length() - 1) {
				ext = s.substring(i + 1).toLowerCase();
			}
			return ext;
		}

		/*
		 * Filter out PNG files in the FC dialog.
		 */
		class IFilter extends FileFilter {
			public boolean accept(File f) {
				if (f.isDirectory())
					return true;
				String ext = getExtension(f);
				return ext != null && (ext.equals("jpg") || ext.equals("jpeg"));
			}

			public String getDescription() {
				return "JPEG Images";
			}
		} // IFilter
	} // FileHandler

	class FGFrame extends JFrame implements ActionListener {
		private static final long serialVersionUID = 1;
		private JMenuItem mSave;
		private JMenuItem mSaveAs;
		private JMenuItem mQuit;
		ComplexPlane plane;
		private FGDimensions dimensions;
		private FGToolbar toolbar;
		private BufferedImage backImage;
		private Graphics2D backGfx;
		private Dimension backDim;

		FGFrame() {
			super(WINDOW_TITLE);

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JMenuBar bar = new JMenuBar();
			{ // build file menu
				JMenu file = new JMenu("File");
				file.setMnemonic(KeyEvent.VK_F);

				file.addSeparator();

				mSave = new JMenuItem("Save");
				mSave.setMnemonic(KeyEvent.VK_S);
				mSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
						ActionEvent.CTRL_MASK));
				mSave.addActionListener(this);
				file.add(mSave);
				mSaveAs = new JMenuItem("Save as...");
				mSaveAs.setMnemonic(KeyEvent.VK_A);
				mSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
						ActionEvent.CTRL_MASK));
				mSaveAs.addActionListener(this);
				file.add(mSaveAs);
				file.addSeparator();
				mQuit = new JMenuItem("Quit");
				mQuit.setMnemonic(KeyEvent.VK_Q);
				mQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
						ActionEvent.CTRL_MASK));
				mQuit.addActionListener(this);
				file.add(mQuit);
				bar.add(file);
			}
			setJMenuBar(bar);

			getContentPane().setLayout(new BorderLayout());

			plane = new ComplexPlane();
			getContentPane().add(plane, BorderLayout.CENTER);

			dimensions = new FGDimensions();
			getContentPane().add(dimensions, BorderLayout.SOUTH);

			toolbar = new FGToolbar();
			getContentPane().add(toolbar, BorderLayout.NORTH);

			pack();
		}

		void enableComponents(boolean enabled) {
			toolbar.enableComponents(enabled);
			dimensions.enableComponents(enabled);
			plane.enableZoom(enabled);
			setStatus(enabled ? fileHandler.filename : "Rendering...");
		}

		void clear() {
			plane.clear();
			plane.repaint();
		}

		void setStatus(String s) {
			dimensions.setStatus(s);
		}

		void setDimensions() {
			dimensions.setDimensions();
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JMenuItem) {
				JMenuItem item = (JMenuItem) e.getSource();
				if (item == mSave) {
					fileHandler.save();
				} else if (item == mSaveAs) {
					fileHandler.saveAs();
				} else if (item == mQuit) {
					signal(QUIT);
				}
			}
		}

		class ComplexPlane extends JPanel implements MouseListener,
				MouseMotionListener {
			private static final long serialVersionUID = 1;

			private int mx1, my1, mx2, my2; // mouse positions

			private boolean dragging;

			ComplexPlane() {
				super();

				setPreferredSize(new Dimension(prefWidth, prefHeight));
				setMinimumSize(getPreferredSize());
				setMaximumSize(getPreferredSize());
				setSize(getPreferredSize());
				enableZoom(true);
				setBackground(Color.WHITE);
				setBorder(new SoftBevelBorder(BevelBorder.RAISED));
			}

			void enableZoom(boolean enabled) {
				if (enabled) {
					addMouseListener(this);
					addMouseMotionListener(this);
				} else {
					removeMouseListener(this);
					removeMouseMotionListener(this);
				}
			}

			void clear() {
				backGfx.setColor(Color.WHITE);
				backGfx.fillRect(0, 0, backDim.width, backDim.height);
				backGfx.setColor(Color.lightGray);
				int remin = (int) Math.ceil(minReal);
				int remax = (int) Math.floor(maxReal);
				int immin = (int) Math.ceil(minImag);
				int immax = (int) Math.floor(maxImag);
				double deltaRe = maxReal - minReal;
				double deltaIm = maxImag - minImag;
				int origx = (int) (backDim.width * (-minReal) / deltaRe);
				int origy = (int) (backDim.height * maxImag / deltaIm);
				for (int x = remin; x <= remax; ++x) {
					int offX = (int) (backDim.width * (x - minReal) / deltaRe);
					backGfx.drawLine(offX, origy - 3, offX, origy + 3);
					if (x != 0) {
						backGfx.drawString(Integer.toString(x), offX - 5,
								origy + 20);
					}
				}
				for (int y = immin; y <= immax; ++y) {
					int offY = (int) (backDim.height * (maxImag - y) / deltaIm);
					backGfx.drawLine(origx - 3, offY, origx + 3, offY);
					if (y != 0) {
						backGfx.drawString(Integer.toString(y), origx - 20,
								offY + 5);
					}
				}
				if (maxReal > 0 && minReal < 0) {
					backGfx.drawLine(origx, 0, origx, backDim.height);
				}
				if (maxImag > 0 && minImag < 0) {
					backGfx.drawLine(0, origy, backDim.width, origy);
				}

				backGfx.setColor(Color.BLACK);
			}

			void putData(int x, int y, Color[][] c, int pw, int ph) {
				int height = c.length;
				int width = c[0].length;
				for (int h = 0; h < height; ++h) {
					for (int w = 0; w < width; ++w) {
						backGfx.setColor(c[h][w]);
						backGfx.fillRect(pw * w + x, ph * h + y, pw, ph);
					}
				}
				repaint();
			}

			private int startx, prevx, starty, prevy;

			void zoomRect() {
				int x, y, w, h;
				if (prevx > startx) {
					x = startx;
					w = prevx - startx;
				} else {
					x = prevx;
					w = startx - prevx;
				}
				if (prevy > starty) {
					y = starty;
					h = prevy - starty;
				} else {
					y = prevy;
					h = starty - prevy;
				}
				backGfx.setColor(Color.RED);
				backGfx.fillRect(x, y, w, h);
				repaint();
			}

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				update(g);
			}

			public void update(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				if (backGfx == null) {
					backDim = getSize();
					backImage = (BufferedImage) createImage(backDim.width,
							backDim.height);
					backGfx = backImage.createGraphics();
					clear();
				}
				if (backDim.width != getWidth()
						|| backDim.height != getHeight()) {
					BufferedImage tmp = backImage;
					backDim = getSize();
					backImage = (BufferedImage) createImage(backDim.width,
							backDim.height);
					backGfx = backImage.createGraphics();
					backGfx.drawImage(tmp, 0, 0, backDim.width, backDim.height,
							null);
				}
				if (g2 != null && backGfx != null) {
					g2.drawImage(backImage, 0, 0, backDim.width,
							backDim.height, null);
				}
			}

			public void mouseClicked(MouseEvent e) { /* Don't Care */
			}

			public void mouseEntered(MouseEvent e) {
				dimensions.mousePos.setForeground(Color.RED);
			}

			public void mouseExited(MouseEvent e) {
				dimensions.mousePos.setForeground(Color.BLACK);
				dimensions.mousePos.setText(dimensions.mousePos.getText());
			}

			public void mousePressed(MouseEvent e) {
				if (!dragging) {
					backGfx.setXORMode(Color.WHITE);
					dragging = true;
					setStatus("Zooming...");
					startx = prevx = mx1 = e.getX();
					starty = prevy = my1 = e.getY();
					zoomRect();
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (dragging) {
					dragging = false;
					zoomRect();
					backGfx.setPaintMode();
					setStatus(fileHandler.filename);
					mx2 = e.getX();
					my2 = e.getY();

					int xmin = mx1 < mx2 ? mx1 : mx2;
					int xmax = mx1 > mx2 ? mx1 : mx2;
					int ymin = my1 < my2 ? my1 : my2;
					int ymax = my1 > my2 ? my1 : my2;
					int deltax = xmax - xmin;
					int deltay = ymax - ymin;
					int sqdist = deltax * deltax + deltay * deltay;
					if (sqdist > 0 && xmax > xmin && ymax > ymin) {
						double deltaRe = maxReal - minReal;
						double deltaIm = maxImag - minImag;
						int w = getWidth();
						int h = getHeight();
						double newMinReal = minReal + deltaRe * xmin / w;
						double newMaxReal = minReal + deltaRe * xmax / w;
						double newMinImag = maxImag - deltaIm * ymax / h;
						double newMaxImag = maxImag - deltaIm * ymin / h;
						minReal = newMinReal;
						maxReal = newMaxReal;
						minImag = newMinImag;
						maxImag = newMaxImag;
						setDimensions();
						signal(ZOOM);
					}
				}
			}

			public void mouseDragged(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				int w = getWidth();
				int h = getHeight();
				double deltaRe = maxReal - minReal;
				double deltaIm = maxImag - minImag;
				double re = minReal + deltaRe * x / w;
				double im = maxImag - deltaIm * y / h;
				dimensions.mousePos.setText("(" + re + " , " + im + ")");
				if (dragging) {
					zoomRect();
					prevx = x;
					prevy = y;
					zoomRect();
				}
			}

			public void mouseMoved(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				int w = getWidth();
				int h = getHeight();
				double deltaRe = maxReal - minReal;
				double deltaIm = maxImag - minImag;
				double re = minReal + deltaRe * x / w;
				double im = maxImag - deltaIm * y / h;
				dimensions.mousePos.setText("(" + re + " , " + im + ")");
			}
		} // class ComplexPlane

		class FGDimensions extends JPanel implements ActionListener {
			private static final long serialVersionUID = 1;

			private JTextField tfMinReal;

			private JTextField tfMaxReal;

			private JTextField tfMinImag;

			private JTextField tfMaxImag;

			// private JButton bReset;
			private JLabel mousePos;

			private JLabel status;

			FGDimensions() {
				super();

				tfMinReal = new JTextField(Double.toString(minReal), 15);
				tfMinReal.addActionListener(this);
				tfMaxReal = new JTextField(Double.toString(maxReal), 15);
				tfMaxReal.addActionListener(this);
				tfMinImag = new JTextField(Double.toString(minImag), 15);
				tfMinImag.addActionListener(this);
				tfMaxImag = new JTextField(Double.toString(maxImag), 15);
				tfMaxImag.addActionListener(this);

				// bReset = new JButton("Reset dimensions");
				// bReset.addActionListener(this);

				mousePos = new JLabel(" ");

				status = new JLabel(fileHandler.filename);
				status.setForeground(Color.BLUE);

				setLayout(new BorderLayout());
				setBorder(new TitledBorder("Dimensions"));

				{ // build topPanel
					JPanel topPanel = new JPanel();
					topPanel.setLayout(new GridLayout(1, 2));

					{ // realAxis
						JPanel realAxis = new JPanel();
						realAxis.setBorder(new TitledBorder(new EtchedBorder(),
								"Real axis"));
						realAxis.setLayout(new GridLayout(1, 2));

						{ // min value
							JPanel remin = new JPanel();
							remin.setLayout(new GridLayout(2, 1));
							remin.add(new JLabel("Min value:"));
							remin.add(tfMinReal);
							realAxis.add(remin);
						}

						{ // max value
							JPanel remax = new JPanel();
							remax.setLayout(new GridLayout(2, 1));
							remax.add(new JLabel("Max value:"));
							remax.add(tfMaxReal);
							realAxis.add(remax);
						}

						topPanel.add(realAxis);
					}

					{ // imagAxis
						JPanel imagAxis = new JPanel();
						imagAxis.setBorder(new TitledBorder(new EtchedBorder(),
								"Imaginary axis"));
						imagAxis.setLayout(new GridLayout(1, 2));

						{ // min value
							JPanel immin = new JPanel();
							immin.setLayout(new GridLayout(2, 1));
							immin.add(new JLabel("Min value:"));
							immin.add(tfMinImag);
							imagAxis.add(immin);
						}

						{ // max value
							JPanel immax = new JPanel();
							immax.setLayout(new GridLayout(2, 1));
							immax.add(new JLabel("Max value:"));
							immax.add(tfMaxImag);
							imagAxis.add(immax);
						}

						topPanel.add(imagAxis);
					}

					add(topPanel, BorderLayout.CENTER);
				}

				{ // build bottomPanel
					JPanel bottomPanel = new JPanel();
					bottomPanel.setLayout(new GridLayout(2, 1));

					{ // mousePos field
						JPanel mouse = new JPanel();
						mouse.add(mousePos);
						bottomPanel.add(mouse);
					}

					{ // status field
						JPanel statusp = new JPanel();
						statusp.setLayout(new FlowLayout(FlowLayout.LEFT));
						statusp.setBorder(new EtchedBorder());
						statusp.setBackground(Color.WHITE);
						statusp.add(status);
						bottomPanel.add(statusp);
					}

					add(bottomPanel, BorderLayout.SOUTH);
				}
			} // FGDimensions()

			void enableComponents(boolean enabled) {
				tfMinReal.setEnabled(enabled);
				tfMaxReal.setEnabled(enabled);
				tfMinImag.setEnabled(enabled);
				tfMaxImag.setEnabled(enabled);
				// bReset.setEnabled(enabled);
			}

			void setDimensions() {
				tfMinReal.setText(Double.toString(minReal));
				tfMaxReal.setText(Double.toString(maxReal));
				tfMinImag.setText(Double.toString(minImag));
				tfMaxImag.setText(Double.toString(maxImag));
			}

			void setStatus(String status) {
				this.status.setText(status);
				this.status.repaint();
			}

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() instanceof JTextField) {
					JTextField tf = (JTextField) e.getSource();
					String text = tf.getText();
					double value = 0;
					try {
						value = Double.parseDouble(text);
					} catch (NumberFormatException nfe) {
						setDimensions();
						return;
					}

					if (tf == tfMinReal && value < maxReal) {
						minReal = value;
					} else if (tf == tfMaxReal && value > minReal) {
						maxReal = value;
					} else if (tf == tfMinImag && value < maxImag) {
						minImag = value;
					} else if (tf == tfMaxImag && value > minImag) {
						maxImag = value;
					} else {
						setDimensions();
						return;
					}
					setDimensions();
					signal(ZOOM);
				}
				// } else if (e.getSource() instanceof JButton) {
				// JButton b = (JButton) e.getSource();
				// if (b == bReset) {
				// signal(RESET);
				// }
				// }
			}
		} // class FGDimensions

		class FGToolbar extends JPanel implements ActionListener {
			// private JButton bOpen;
			private static final long serialVersionUID = 1;

			private JButton bSave;

			private JButton bRender;

			private JButton bReset;

			private JButton bQuit;

			private JComboBox cbResolution;

			private JComboBox cbMode;

			FGToolbar() {
				super();

				{ // create buttons
					bSave = new JButton("Save...");
					bSave.setToolTipText("Save image to a file");
					bSave.addActionListener(this);

					bRender = new JButton("Render");
					bRender.setToolTipText("Start the fractal rendering");
					bRender.addActionListener(this);

					bReset = new JButton("Reset");
					bReset.setToolTipText("Reset plane axes");
					bReset.addActionListener(this);

					bQuit = new JButton("Quit");
					bQuit.setToolTipText("Quit application");
					bQuit.addActionListener(this);
				}

				{ // create combos
					String[] resolutions = { "Very low", "Low", "Medium",
							"High", "Very high" };
					cbResolution = new JComboBox(resolutions);
					cbResolution.setToolTipText("Set image resolution");
					int index = (int) (Math.round((Math.log(resolution) - Math
							.log(RESOLUTION_VERY_LOW))
							/ Math.log(2)));
					cbResolution.setSelectedIndex(index);
					cbResolution.addActionListener(this);

					String[] modes = { "Black/White", "Color" };
					cbMode = new JComboBox(modes);
					cbMode.setToolTipText("Set image mode");
					index = (int) (Math.round((Math.log(mode) - Math
							.log(MODE_BW))
							/ Math.log(2)));
					cbMode.setSelectedIndex(index);
					cbMode.addActionListener(this);
				}

				setBorder(new TitledBorder(new EtchedBorder(), "Toolbar"));
				// setLayout(new GridLayout(1, 3));
				setLayout(new BorderLayout());

				{ // build files panel
					JPanel files = new JPanel();
					files.setBorder(new TitledBorder(new EtchedBorder(),
							"Files"));
					// files.setLayout(new GridLayout(1, 2));
					files.setLayout(new FlowLayout());
					// files.add(bOpen);
					files.add(bSave);
					add(files, BorderLayout.WEST);
				}

				{ // build actions panel
					JPanel actions = new JPanel();
					actions.setBorder(new TitledBorder(new EtchedBorder(),
							"Actions"));
					actions.setLayout(new FlowLayout(FlowLayout.LEFT));
					actions.add(bRender);
					actions.add(bReset);
					actions.add(bQuit);
					add(actions, BorderLayout.CENTER);
				}

				{ // build res/mode panel
					JPanel resmode = new JPanel();
					resmode.setBorder(new TitledBorder(new EtchedBorder(),
							"Resolution / Mode"));
					resmode.setLayout(new GridLayout(1, 2));
					resmode.add(cbResolution);
					resmode.add(cbMode);
					add(resmode, BorderLayout.EAST);
				}
			}

			void enableComponents(boolean enabled) {
				bSave.setEnabled(enabled);
				bRender.setEnabled(enabled);
				bReset.setEnabled(enabled);
				bQuit.setEnabled(enabled);
				cbResolution.setEnabled(enabled);
				cbMode.setEnabled(enabled);
			}

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() instanceof JButton) {
					JButton b = (JButton) e.getSource();
					// if (b == bOpen) {
					// fileHandler.open();
					// } else if (b == bSave) {
					if (b == bSave) {
						fileHandler.save();
					} else if (b == bRender) {
						signal(RENDER);
					} else if (b == bReset) {
						signal(RESET);
					} else if (b == bQuit) {
						signal(QUIT);
					}
				} else if (e.getSource() instanceof JComboBox) {
					JComboBox cb = (JComboBox) e.getSource();
					if (cb == cbResolution) {
						resolution = RESOLUTION_VERY_LOW << cb
								.getSelectedIndex();
					} else if (cb == cbMode) {
						mode = MODE_BW << cb.getSelectedIndex();
					}
				}
			}
		} // class FGToolbar
	} // class FGFrame
} // class MandelbrotGUI
