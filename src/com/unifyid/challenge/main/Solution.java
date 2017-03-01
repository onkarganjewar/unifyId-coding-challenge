package com.unifyid.challenge.main;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * @author Onkar Ganjewar
 */
public class Solution {
	private final static String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {
		String url = "https://www.random.org/integers/?num=8009&min=0&max=255&col=1&base=10&format=plain&rnd=new";
		List<Integer> numbersList = sendGet(url);
		generateImage(numbersList);

	}

	/**
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	// HTTP GET request
	private static List<Integer> sendGet(String url) throws Exception {

		List<Integer> randomList = new ArrayList<>();

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
//		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
//		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
//			response.append(inputLine);
			int num = Integer.parseInt(inputLine);
			randomList.add(num);
		}
		in.close();

		return randomList;
	}

	private static void generateImage(List<Integer> randomInts) {
		List<Integer> randomList = new ArrayList<>();
		// image dimension
		int width = 128;
		int height = 128;
		// create buffered image object img
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		// file object
		File f = null;
		// List<Integer> randomInts = new ArrayList<>();
		// for (int i = 0; i < 9999; i++) {
		// int getNum = (int) (Math.random() * 200);
		// randomInts.add(getNum);
		// }
		for (int j = 0; j < 9; j++) {
			for (int k = 0; k < 7999; k++) {
				int index = j + 1;
				int getNum = randomInts.get(k);
				getNum += index;
				randomList.add(getNum);
			}
		}
		int count = 0;
		int foo[] = { count++, count++, count++ };
		System.out.println("Value f=" + count);
		// create random image pixel by pixel
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// count++;
				if (count >= 69909)
					count = 0;
				int a = (int) randomList.get(count);
				int r = (int) randomList.get(++count);
				int g = (int) randomList.get(++count);
				int b = (int) randomList.get(++count); // blue

				int p = (a << 24) | (r << 16) | (g << 8) | b; // pixel

				img.setRGB(x, y, p);
			}
		}
		System.out.println("value of count" + count);
		// write image
		try {
			String path = "D:\\Image\\Output5.jpg";
			path = path.replace("\\", "/");
			f = new File(path);
			ImageIO.write(img, "jpg", f);
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}

}
