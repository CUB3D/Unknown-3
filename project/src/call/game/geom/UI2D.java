/**
 * Copyright 2015 Callum A. D. Thomson
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package call.game.geom;

import java.awt.Font;
import java.awt.Point;

import javax.media.opengl.GL2;

import call.game.main.Unknown;

import com.jogamp.opengl.util.awt.TextRenderer;

/**
 * @author Callum, AKA: CUB3D
 */
public class UI2D
{
	/**
	 * Draws a white rectangle at the given location with the given size.
	 * 
	 * @param x The x coordinate of the bottom left of the rectangle in pixels.
	 * @param y The y coordinate of the bottom left of the rectangle in pixels.
	 * @param width The width of the rectangle in pixels.
	 * @param height The height of the rectangle in pixels.
	 */
	public static void rect(double x, double y, double width, double height)
	{
		rect(x, y, width, height, 0xFFFFFFFF);
	}
	
	/**
	 * Draws a rectangle at the given location with the given size and colour.
	 * 
	 * @param x The x coordinate of the bottom left of the rectangle in pixels.
	 * @param y The y coordinate of the bottom left of the rectangle in pixels.
	 * @param width The width of the rectangle in pixels.
	 * @param height The height of the rectangle in pixels.
	 * @param col The colour of the rectangle in the format 0xAARRGGBB.
	 */
	public static void rect(double x, double y, double width, double height, int col)
	{
		GL2 gl = Unknown.getGL();

		int blue = (col >> 0) & 0xFF;
		int green = (col >> 8) & 0xFF;
		int red = (col >> 16) & 0xFF;
		int alpha = (col >> 24) & 0xFF;

		gl.glDisable(GL2.GL_LIGHTING);
		gl.glDisable(GL2.GL_TEXTURE_2D);
		
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		
		gl.glBegin(GL2.GL_QUADS);

		gl.glColor4f(red / 255.0f, green / 255.0f, blue / 255.0f, alpha / 255.0f);

		gl.glVertex2d(x, y);
		gl.glVertex2d(x, y + height);
		gl.glVertex2d(x + width, y + height);
		gl.glVertex2d(x + width, y);

		gl.glEnd();
	}
	
	/**
	 * Draws a rectangular outline at the given location with the given size and colour.
	 * 
	 * @param x The x coordinate of the bottom left of the outline in pixels.
	 * @param y The y coordinate of the bottom left of the outline in pixels.
	 * @param width The width of the outline in pixels.
	 * @param height The height of the outline in pixels.
	 * @param col The colour of the outline in the format 0xAARRGGBB.
	 */
	public static void outlineRect(double x, double y, double width, double height, int col)
	{
		int lineWidth = 8;
		
		GL2 gl = Unknown.getGL();

		int blue = (col >> 0) & 0xFF;
		int green = (col >> 8) & 0xFF;
		int red = (col >> 16) & 0xFF;
		int alpha = (col >> 24) & 0xFF;

		gl.glDisable(GL2.GL_LIGHTING);
		gl.glDisable(GL2.GL_TEXTURE_2D);
		
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		
		gl.glBegin(GL2.GL_LINES);
		
		gl.glLineWidth(lineWidth);
		
		gl.glColor4f(red / 255.0f, green / 255.0f, blue / 255.0f, alpha / 255.0f);

		gl.glVertex2d(x, y);
		gl.glVertex2d(x, y + height);
		
		gl.glVertex2d(x, y);
		gl.glVertex2d(x + width, y);
		
		gl.glVertex2d(x, y + height);
		gl.glVertex2d(x + width, y + height);
		
		gl.glVertex2d(x + width, y);
		gl.glVertex2d(x + width, y + height);
		
		gl.glEnd();
	}

	/**
	 * Draws a white square at the given location with the given size.
	 * 
	 * @param x The x coordinate of the bottom left of the square in pixels.
	 * @param y The y coordinate of the bottom left of the square in pixels.
	 * @param size The size of the square in pixels.
	 */
	public static void square(double x, double y, double size)
	{
		square(x, y, size, 0xFFFFFFFF);
	}
	
	/**
	 * Draws a square at the given location with the given size and colour.
	 * 
	 * @param x The x coordinate of the bottom left of the square in pixels.
	 * @param y The y coordinate of the bottom left of the square in pixels.
	 * @param size The size of the square in pixels.
	 * @param col The colour of the rectangle in the format 0xAARRGGBB.
	 */
	public static void square(double x, double y, double size, int col)
	{
		rect(x, y, size, size, col);
	}
	
	/**
	 * Draws a white triangle at the given location with the given size.
	 * 
	 * @param x The x coordinate of the bottom left of the triangle in pixels.
	 * @param y The y coordinate of the bottom left of the triangle in pixels.
	 * @param width The width of the triangle in pixels.
	 * @param height The height of the triangle in pixels.
	 */
	public static void triangle(double x, double y, double width, double height)
	{
		triangle(x, y, width, height, 0xFFFFFFFF);
	}
	
	/**
	 * Draws a triangle at the given location with the given size and colour.
	 * 
	 * @param x The x coordinate of the bottom left of the triangle in pixels.
	 * @param y The y coordinate of the bottom left of the triangle in pixels.
	 * @param width The width of the triangle in pixels.
	 * @param height The height of the triangle in pixels.
	 * @param col The colour of the triangle in the format 0xAARRGGBB.
	 */
	public static void triangle(double x, double y, double width, double height, int col)
	{
		GL2 gl = Unknown.getGL();

		int blue = (col >> 0) & 0xFF;
		int green = (col >> 8) & 0xFF;
		int red = (col >> 16) & 0xFF;
		int alpha = (col >> 24) & 0xFF;

		gl.glDisable(GL2.GL_LIGHTING);
		gl.glDisable(GL2.GL_TEXTURE_2D);
		
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		
		gl.glBegin(GL2.GL_TRIANGLES);

		gl.glColor4f(red / 255.0f, green / 255.0f, blue / 255.0f, alpha / 255.0f);

		gl.glVertex2d(x + width / 2, y + height);
		gl.glVertex2d(x, y);
		gl.glVertex2d(x + width, y);

		gl.glEnd();
	}
	
	/**
	 * Draws a white polygon at the given coordinates with the given points.
	 * 
	 * @param x The x coordinate of the bottom left of the polygon in pixels.
	 * @param y The y coordinate of the bottom left of the polygon in pixels.
	 * @param verts An array containing all of the localised points on the polygon.
	 */
	public static void polygon(double x, double y, Point[] verts)
	{
		polygon(x, y, verts, 0xFFFFFFFF);
	}

	/**
	 * Draws a polygon at the given coordinates with the given points and colour.
	 * 
	 * @param x The x coordinate of the bottom left of the polygon in pixels.
	 * @param y The y coordinate of the bottom left of the polygon in pixels.
	 * @param verts An array containing all of the localised points on the polygon.
	 * @param col The colour of the polygon in the format 0xAARRGGBB.
	 */
	public static void polygon(double x, double y, Point[] verts, int col)
	{
		GL2 gl = Unknown.getGL();
		
		int blue = (col >> 0) & 0xFF;
		int green = (col >> 8) & 0xFF;
		int red = (col >> 16) & 0xFF;
		int alpha = (col >> 24) & 0xFF;

		gl.glDisable(GL2.GL_LIGHTING);
		gl.glDisable(GL2.GL_TEXTURE_2D);
		
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		
		gl.glBegin(GL2.GL_POLYGON);

		gl.glColor4f(red / 255.0f, green / 255.0f, blue / 255.0f, alpha / 255.0f);

		for(Point d : verts)
			gl.glVertex2d(x + d.getX(), y + d.getY());

		gl.glEnd();
	}
	
	/**
	 * Draws a white line from the first set of coordinates to the second set of coordinates.
	 * 
	 * @param x1 The first x coordinate of the line in pixels.
	 * @param y1 The first y coordinate of the line in pixels.
	 * @param x2 The second x coordinate of the line in pixels.
	 * @param y2 The second y coordinate of the line in pixels.
	 * @param width The width of the line in pixels.
	 */
	public static void line(double x1, double y1, double x2, double y2, float width)
	{
		line(x1, y1, x2, y2, width, 0xFFFFFFFF);
	}
	
	/**
	 * Draws a line from the first set of coordinates to the second set of coordinates with the given colour.
	 * 
	 * @param x1 The first x coordinate of the line in pixels.
	 * @param y1 The first y coordinate of the line in pixels.
	 * @param x2 The second x coordinate of the line in pixels.
	 * @param y2 The second y coordinate of the line in pixels.
	 * @param width The width of the line in pixels.
	 * @param col The colour of the line in the format 0xAARRGGBB.
	 */
	public static void line(double x1, double y1, double x2, double y2, float width, int col)
	{
		GL2 gl = Unknown.getGL();

		int blue = (col >> 0) & 0xFF;
		int green = (col >> 8) & 0xFF;
		int red = (col >> 16) & 0xFF;
		int alpha = (col >> 24) & 0xFF;

		gl.glDisable(GL2.GL_LIGHTING);
		gl.glDisable(GL2.GL_TEXTURE_2D);
		
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		
		gl.glBegin(GL2.GL_LINES);
		
		gl.glLineWidth(width);
		
		gl.glColor4f(red / 255.0f, green / 255.0f, blue / 255.0f, alpha / 255.0f);

		gl.glVertex2d(x1, y1);
		gl.glVertex2d(x2, y2);
		
		gl.glEnd();
	}
	
	private static Font defaultFont = new Font("Serif", Font.PLAIN, 11);
	private static TextRenderer textRenderer = new TextRenderer(defaultFont);
	
	/**
	 * Draws the given text at the given location.
	 * 
	 * @param text The text to be drawn.
	 * @param x The x coordinate of the bottom left of the text in pixels.
	 * @param y The y coordinate of the bottom left of the text in pixels.
	 */
	public static void renderText(String text, int x, int y)
	{
		GL2 gl = Unknown.getGL();
		
		textRenderer.begin3DRendering();
		
		gl.glPushMatrix();
		
		textRenderer.draw(text, x, y);
		
		textRenderer.flush();
		gl.glPopMatrix();
		
		textRenderer.end3DRendering(); 
	}
}
