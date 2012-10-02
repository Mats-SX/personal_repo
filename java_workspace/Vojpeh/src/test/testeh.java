package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;

public class testeh {

	public static void main(String[] args) throws LineUnavailableException, UnknownHostException {
		Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
		for (Mixer.Info info : mixerInfos) {
			Mixer m = AudioSystem.getMixer(info);
			Line.Info[] lineInfos = m.getSourceLineInfo();
			for (Line.Info lineInfo : lineInfos) {
				System.out.println(info.getName() + "---" + lineInfo);
				Line line = m.getLine(lineInfo);
				System.out.println("\t-----" + line);
			}
			lineInfos = m.getTargetLineInfo();
			for (Line.Info lineInfo : lineInfos) {
				System.out.println(m + "---" + lineInfo);
				Line line = m.getLine(lineInfo);
				System.out.println("\t-----" + line);

			}

		}
		
		System.out.println("------------");
		
		System.out.println(InetAddress.getLocalHost());
		System.out.println(InetAddress.getByName("www.google.com").toString().substring(InetAddress.getByName("www.google.com").toString().indexOf('/')+1));
	}
}
