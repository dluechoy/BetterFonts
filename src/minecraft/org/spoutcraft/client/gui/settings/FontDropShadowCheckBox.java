/*
 * This file is part of Spoutcraft (http://www.spout.org/).
 *
 * Spoutcraft is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spoutcraft is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.spoutcraft.client.gui.settings;

import net.minecraft.client.Minecraft;
import net.minecraft.src.FontRenderer;
import org.spoutcraft.client.SpoutClient;

import org.spoutcraft.client.config.ConfigReader;
import org.spoutcraft.spoutcraftapi.event.screen.ButtonClickEvent;
import org.spoutcraft.spoutcraftapi.gui.GenericCheckBox;

public class FontDropShadowCheckBox extends GenericCheckBox{
	public FontDropShadowCheckBox () {
		super("Drop Shadows");
		setChecked(ConfigReader.fontDropShadowEnabled);
		setTooltip("Enabled by default, Minecraft draws drop shadow for any text in the GUIs and the chat. Disabling this can make some fonts more readable.");
	}

	public FontDropShadowCheckBox(String title) {
		//super(title);
	}

	@Override
	public String getTooltip() {
		if (!isEnabled()) {
			return "This option is not allowed.";
		}
		return super.getTooltip();
	}

	@Override
	public void onButtonClick(ButtonClickEvent event) {
		ConfigReader.fontDropShadowEnabled = this.isChecked();
		ConfigReader.write();
		Minecraft game = SpoutClient.getHandle();
		game.fontRenderer.enableBetterFonts = ConfigReader.betterFontsEnabled; // this way the glyphcache is not flushed.
	}
}