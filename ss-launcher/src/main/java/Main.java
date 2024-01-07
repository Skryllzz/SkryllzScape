import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author ReverendDread on 6/7/2020
 * https://www.rune-server.ee/members/reverenddread/
 * @project kronos-launcher
 */
public class Main {

    public static final String CLIENT_URL = "https://www.dropbox.com/scl/fi/sa5zw09svn2gxtgna27pa/SkryscapeScapeBeta.jar?rlkey=2dvux9g23vofywfi92sofcbm2&dl=1";
    public static String CLIENT_PATH = System.getProperty("user.home") + File.separator + ".skryllzscape" + File.separator;

    private JFrame frame;
    private JProgressBar progress;
    private long readBytes;

    public Main() throws Exception {
        try {

            UIManager.put("ProgressBar.selectionBackground",Color.WHITE);
            UIManager.put("ProgressBar.selectionForeground",Color.LIGHT_GRAY);

            frame = new JFrame("SkryllzScape Launcher");
            ImageIcon icon = new ImageIcon(Main.class.getResource("logo.png"));
            //ImageIcon icon = new ImageIcon(("logo.png"));

            progress = new JProgressBar();
            progress.setForeground(Color.white);
            progress.setBackground(Color.darkGray);

            JLabel label = new JLabel(icon);
            label.setBackground(new Color(0, 0, 0, 0));

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);
            frame.setLocationRelativeTo(null);
            frame.setUndecorated(true);
            frame.setBackground(new Color(0, 0, 0, 0));

            frame.add(label, BorderLayout.NORTH);
            frame.add(progress, BorderLayout.CENTER);
            frame.pack();

            frame.setVisible(true);

            progress.setStringPainted(true);
            progress.setString("Connecting to update server...");

            File file = update();
            if (file == null) {
                JOptionPane.showMessageDialog(frame, "An error has occured while downloading SkryllzScape.", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
                return;
            }
            launch(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }

    private File update() throws Exception {
        URLConnection connection = new URL(CLIENT_URL).openConnection();
        connection.setUseCaches(false);
        connection.setConnectTimeout(30000);
        connection.setReadTimeout(30000);
        File file = new File(CLIENT_PATH + "SSBeta.jar");
        long lastModified = connection.getLastModified();
        long contentLength = connection.getContentLength();
        if (lastModified == -1) {
            System.out.println("Can't validate modified date.");
            return null;
        }
        if ((file.exists()) && (file.lastModified() == lastModified) && (file.length() == contentLength)) {
            System.out.println("Client doesn't need updated.");
            return file;
        }
        progress.setMaximum((int) contentLength);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<File> task = () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    if (i != 0) {
                        CLIENT_PATH = (file.getName().replace(".jar", "" + i) + ".jar");
                    }
                    File newFile = new File(CLIENT_PATH + "SSBETA.jar");
                    if (!newFile.exists())
                        newFile.getParentFile().mkdirs();
                    try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream())) {
                        FileOutputStream fos = new FileOutputStream(newFile);
                        try (BufferedOutputStream bout = new BufferedOutputStream(fos, 1024)) {
                            byte[] data = new byte[1024];
                            int pos;
                            while ((pos = in.read(data, 0, 1024)) >= 0) {
                                readBytes = readBytes + pos;
                                SwingUtilities.invokeLater(() -> {
                                    progress.setString("Downloading client - " + String.format("%.2f", ((double) readBytes / contentLength) * 100D) + "%");
                                    progress.setValue((int) readBytes);
                                });
                                bout.write(data, 0, pos);
                            }
                        }
                    }
                    newFile.setLastModified(lastModified);
                    return newFile;
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
            return null;
        };
        Future<File> result = executor.submit(task);
        return result.get();
    }

    private void launch(File file) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("java", "-jar", file.getName());
        pb.directory(new File(file.getParentFile().getPath()));
        pb.start();
        System.exit(1);
    }

}

