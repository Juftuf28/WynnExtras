package julianh06.wynnextras.features.waypoints;

import com.google.gson.Gson;
import com.wynntils.core.text.StyledText;
import com.wynntils.utils.colors.CustomColor;
import com.wynntils.utils.mc.McUtils;
import com.wynntils.utils.render.FontRenderer;
import com.wynntils.utils.render.RenderUtils;
import com.wynntils.utils.render.type.HorizontalAlignment;
import com.wynntils.utils.render.type.TextShadow;
import com.wynntils.utils.render.type.VerticalAlignment;
import julianh06.wynnextras.core.WynnExtras;
import julianh06.wynnextras.utils.overlays.EasyButton;
import julianh06.wynnextras.utils.overlays.EasyColorPicker;
import julianh06.wynnextras.utils.overlays.EasyDropdown;
import julianh06.wynnextras.utils.overlays.EasySlider;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.stream.Collectors;

import static julianh06.wynnextras.features.waypoints.WaypointScreen.mouseX;
import static julianh06.wynnextras.features.waypoints.WaypointScreen.scaleFactor;

public class CategoryElement {
    public WaypointCategory category;

    public int x;
    public int y;
    public int width;
    public int height;

    public WaypointInput nameInput;
    public EasyButton deleteButton;
    public EasyButton hideAllBlocksButton;
    public EasyButton showAllBlocksButton;
    public EasyButton hideAllNamesButton;
    public EasyButton showAllNamesButton;
    public EasyButton hideAllDistancesButton;
    public EasyButton showAllDistancesButton;
    //public EasyButton exportButton;
    public EasyColorPicker colorPicker;
    public EasySlider alphaSlider;

    Identifier deleteTexture = Identifier.of("wynnextras", "textures/gui/waypoints/deletebutton.png");
    Identifier exportButtonTexture = Identifier.of("wynnextras", "textures/gui/waypoints/exportbutton.png");
    Identifier activeTexture = Identifier.of("wynnextras", "textures/gui/waypoints/checkboxactive.png");
    Identifier backgroundTexture = Identifier.of("wynnextras", "textures/gui/waypoints/categorybackground.png");
    Identifier nameBackgroundTexture = Identifier.of("wynnextras", "textures/gui/waypoints/nameinputcategory.png");
    Identifier colorPickerBorderTexture = Identifier.of("wynnextras", "textures/gui/waypoints/colorpickerborder.png");

    public CategoryElement(int x, int y, int width, int height, WaypointCategory category) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        deleteButton = new EasyButton(-1, -1, -1, -1) {
            @Override
            public void click() {
                WaypointData.INSTANCE.activePackage.categories.remove(category);
            }
        };

        hideAllBlocksButton = new EasyButton(-1, -1, -1, -1) {
            @Override
            public void click() {
                for(WaypointElement element : WaypointScreen.elements) {
                    if(element.waypoint.categoryName == null) continue;
                    if(element.waypoint.categoryName.isEmpty()) continue;
                    if(element.waypoint.categoryName.equals(category.name)) {
                        element.waypoint.show = false;
                        }
                }
                McUtils.sendMessageToClient(WynnExtras.addWynnExtrasPrefix(
                        Text.of("Now hiding block highlights for all waypoints of category " + category.name)));
            }
        };

        showAllBlocksButton = new EasyButton(-1, -1, -1, -1) {
            @Override
            public void click() {
                for(WaypointElement element : WaypointScreen.elements) {
                    if(element.waypoint.categoryName == null) continue;
                    if(element.waypoint.categoryName.isEmpty()) continue;
                    if(element.waypoint.categoryName.equals(category.name)) {
                        element.waypoint.show = true;
                        }
                }
                McUtils.sendMessageToClient(WynnExtras.addWynnExtrasPrefix(
                        Text.of("Now showing block highlights for all waypoints of category " + category.name)));
            }
        };

        hideAllNamesButton = new EasyButton(-1, -1, -1, -1) {
            @Override
            public void click() {
                for(WaypointElement element : WaypointScreen.elements) {
                    if(element.waypoint.categoryName == null) continue;
                    if(element.waypoint.categoryName.isEmpty()) continue;
                    if(element.waypoint.categoryName.equals(category.name)) {
                        element.waypoint.showName = false;
                        }
                }
                McUtils.sendMessageToClient(WynnExtras.addWynnExtrasPrefix(
                        Text.of("Now hiding names of all waypoints of category " + category.name)));
            }
        };

        showAllNamesButton = new EasyButton(-1, -1, -1, -1) {
            @Override
            public void click() {
                for(WaypointElement element : WaypointScreen.elements) {
                    if(element.waypoint.categoryName == null) continue;
                    if(element.waypoint.categoryName.isEmpty()) continue;
                    if(element.waypoint.categoryName.equals(category.name)) {
                        element.waypoint.showName = true;
                        }
                }
                McUtils.sendMessageToClient(WynnExtras.addWynnExtrasPrefix(
                        Text.of("Now showing names of all waypoints of category " + category.name)));
            }
        };

        hideAllDistancesButton = new EasyButton(-1, -1, -1, -1) {
            @Override
            public void click() {
                for(WaypointElement element : WaypointScreen.elements) {
                    if(element.waypoint.categoryName == null) continue;
                    if(element.waypoint.categoryName.isEmpty()) continue;
                    if(element.waypoint.categoryName.equals(category.name)) {
                        element.waypoint.showDistance = false;
                        }
                }
                McUtils.sendMessageToClient(WynnExtras.addWynnExtrasPrefix(
                        Text.of("Now hiding distances to all waypoints of category " + category.name)));
            }
        };

        showAllDistancesButton = new EasyButton(-1, -1, -1, -1) {
            @Override
            public void click() {
                for(WaypointElement element : WaypointScreen.elements) {
                    if(element.waypoint.categoryName == null) continue;
                    if(element.waypoint.categoryName.isEmpty()) continue;
                    if(element.waypoint.categoryName.equals(category.name)) {
                        element.waypoint.showDistance = true;
                    }
                }
                McUtils.sendMessageToClient(WynnExtras.addWynnExtrasPrefix(
                        Text.of("Now showing distances to all waypoints of category " + category.name)));
            }
        };

//        exportButton = new EasyButton(-1, -1, -1, -1) {
//            @Override
//            public void click() {
//                WaypointData export = new WaypointData();
//                export.waypoints = WaypointData.INSTANCE.waypoints.stream()
//                        .filter(w -> w.categoryName.equals(category.name))
//                        .collect(Collectors.toList());
//
//                export.categories = List.of(category);
//
//                String json = new Gson().toJson(export);
//                MinecraftClient.getInstance().keyboard.setClipboard(json);
//                WaypointData.save();
//
//                String waypointString = " waypoints";
//                if(export.waypoints.size() == 1) waypointString = " waypoint";
//                String categoryString = " categories.";
//                if(export.categories.size() == 1) categoryString = " category.";
//                McUtils.sendMessageToClient(WynnExtras.addWynnExtrasPrefix(Text.of("Exported " + export.waypoints.size() + waypointString + " and " + export.categories.size() + categoryString)));
//
//            }
//        };

        colorPicker = new EasyColorPicker(-1, -1, -1, -1);
        colorPicker.setSelectedColor(category.color);

        nameInput = new WaypointInput(x, y, 10, width);
        nameInput.setInput(category.name);

        alphaSlider = new EasySlider(-1, -1, -1, -1, 0, 1, category.alpha, false, val -> {
            category.alpha = val;
        });
    }

    public CategoryElement(WaypointCategory category) {
        this(-1, -1, -1, -1, category);
    }

    public void draw(int x, int y, int width, int height, DrawContext context) {
        if (GLFW.glfwGetMouseButton(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_1) == GLFW.GLFW_PRESS && alphaSlider.dragging) {
            alphaSlider.updateValueFromMouse(mouseX);
        } else {
            alphaSlider.dragging = false;
        }

        this.width = width;
        this.height = height;
        RenderUtils.drawTexturedRect(context.getMatrices(), backgroundTexture, x, y, width + 1, height + 2, width + 1, height + 2);

        RenderUtils.drawTexturedRect(context.getMatrices(), nameBackgroundTexture, x + 3 * 3f / scaleFactor, y + 2.5f * 3 / scaleFactor, (float) (180 * 3) / scaleFactor, (float) (13 * 3) / scaleFactor, 180 * 3 / scaleFactor, 13 * 3 / scaleFactor);
        nameInput.setX(x + 3 * 3 / scaleFactor);
        nameInput.setY(y + 4 * 3 / scaleFactor);
        nameInput.setWidth(180 * 3 / scaleFactor);
        nameInput.setHeight(12 * 3 / scaleFactor);
        nameInput.drawWithoutBackgroundButWithSearchtext(context, CustomColor.fromHexString("FFFFFF"));
        //nameInput.draw(context);

        //FontRenderer.getInstance().renderText(context.getMatrices(), StyledText.fromComponent(Text.of(waypoint.name + " ")), x, y + 10, CustomColor.fromHexString("ffffff"), HorizontalAlignment.LEFT, VerticalAlignment.TOP, TextShadow.NORMAL, 3f / scaleFactor);
        deleteButton.setX(x + width - 16 * 3 / scaleFactor);
        deleteButton.setY(y + 2 * 3 / scaleFactor);
        deleteButton.setWidth(15f * 3 / scaleFactor);
        deleteButton.setHeight(15f * 3 / scaleFactor);
        deleteButton.drawWithTexture(context, deleteTexture);

        showAllBlocksButton.setX(x + 9 / scaleFactor);
        showAllBlocksButton.setY(y + 20 * 3 / scaleFactor);
        showAllBlocksButton.setWidth(15f * 3 / scaleFactor);
        showAllBlocksButton.setHeight(15f * 3 / scaleFactor);
        showAllBlocksButton.drawWithTexture(context, activeTexture);

        hideAllBlocksButton.setX(x + 9 / scaleFactor);
        hideAllBlocksButton.setY(y + 40 * 3 / scaleFactor);
        hideAllBlocksButton.setWidth(15f * 3 / scaleFactor);
        hideAllBlocksButton.setHeight(15f * 3 / scaleFactor);
        hideAllBlocksButton.drawWithTexture(context, deleteTexture);

        FontRenderer.getInstance().renderText(context.getMatrices(), StyledText.fromComponent(Text.of("Enable/")), x + (float) (20 * 3) / scaleFactor, y + 25f * 3 / scaleFactor, CustomColor.fromHexString("ffffff"), HorizontalAlignment.LEFT, VerticalAlignment.MIDDLE, TextShadow.NORMAL, 2.75f / scaleFactor);
        FontRenderer.getInstance().renderText(context.getMatrices(), StyledText.fromComponent(Text.of("Disable")), x + (float) (20 * 3) / scaleFactor, y + 34f * 3 / scaleFactor, CustomColor.fromHexString("ffffff"), HorizontalAlignment.LEFT, VerticalAlignment.MIDDLE, TextShadow.NORMAL, 2.75f / scaleFactor);
        FontRenderer.getInstance().renderText(context.getMatrices(), StyledText.fromComponent(Text.of("Highlight")), x + (float) (20 * 3) / scaleFactor, y + 43f * 3 / scaleFactor, CustomColor.fromHexString("ffffff"), HorizontalAlignment.LEFT, VerticalAlignment.MIDDLE, TextShadow.NORMAL, 2.75f / scaleFactor);
        FontRenderer.getInstance().renderText(context.getMatrices(), StyledText.fromComponent(Text.of("For all")), x + (float) (20 * 3) / scaleFactor, y + 52f * 3 / scaleFactor, CustomColor.fromHexString("ffffff"), HorizontalAlignment.LEFT, VerticalAlignment.MIDDLE, TextShadow.NORMAL, 2.75f / scaleFactor);


        showAllNamesButton.setX(x + 64 * 3 / scaleFactor);
        showAllNamesButton.setY(y + 20 * 3 / scaleFactor);
        showAllNamesButton.setWidth(15f * 3 / scaleFactor);
        showAllNamesButton.setHeight(15f * 3 / scaleFactor);
        showAllNamesButton.drawWithTexture(context, activeTexture);

        hideAllNamesButton.setX(x + 64 * 3 / scaleFactor);
        hideAllNamesButton.setY(y + 40 * 3 / scaleFactor);
        hideAllNamesButton.setWidth(15f * 3 / scaleFactor);
        hideAllNamesButton.setHeight(15f * 3 / scaleFactor);
        hideAllNamesButton.drawWithTexture(context, deleteTexture);

        FontRenderer.getInstance().renderText(context.getMatrices(), StyledText.fromComponent(Text.of("Enable/")), x + (float) (81 * 3) / scaleFactor, y + 25f * 3 / scaleFactor, CustomColor.fromHexString("ffffff"), HorizontalAlignment.LEFT, VerticalAlignment.MIDDLE, TextShadow.NORMAL, 2.75f / scaleFactor);
        FontRenderer.getInstance().renderText(context.getMatrices(), StyledText.fromComponent(Text.of("Disable")), x + (float) (81 * 3) / scaleFactor, y + 34f * 3 / scaleFactor, CustomColor.fromHexString("ffffff"), HorizontalAlignment.LEFT, VerticalAlignment.MIDDLE, TextShadow.NORMAL, 2.75f / scaleFactor);
        FontRenderer.getInstance().renderText(context.getMatrices(), StyledText.fromComponent(Text.of("Name")), x + (float) (81 * 3) / scaleFactor, y + 43f * 3 / scaleFactor, CustomColor.fromHexString("ffffff"), HorizontalAlignment.LEFT, VerticalAlignment.MIDDLE, TextShadow.NORMAL, 2.75f / scaleFactor);
        FontRenderer.getInstance().renderText(context.getMatrices(), StyledText.fromComponent(Text.of("For all")), x + (float) (81 * 3) / scaleFactor, y + 52f * 3 / scaleFactor, CustomColor.fromHexString("ffffff"), HorizontalAlignment.LEFT, VerticalAlignment.MIDDLE, TextShadow.NORMAL, 2.75f / scaleFactor);


        showAllDistancesButton.setX(x + 119 * 3 / scaleFactor);
        showAllDistancesButton.setY(y + 20 * 3 / scaleFactor);
        showAllDistancesButton.setWidth(15f * 3 / scaleFactor);
        showAllDistancesButton.setHeight(15f * 3 / scaleFactor);
        showAllDistancesButton.drawWithTexture(context, activeTexture);

        hideAllDistancesButton.setX(x + 119 * 3 / scaleFactor);
        hideAllDistancesButton.setY(y + 40 * 3 / scaleFactor);
        hideAllDistancesButton.setWidth(15f * 3 / scaleFactor);
        hideAllDistancesButton.setHeight(15f * 3 / scaleFactor);
        hideAllDistancesButton.drawWithTexture(context, deleteTexture);

//        exportButton.setX(x + width / 2 - 150 / scaleFactor);
//        exportButton.setY(y + height - 53 / scaleFactor);
//        exportButton.setWidth(300f / scaleFactor);
//        exportButton.setHeight(36f / scaleFactor);
//        exportButton.drawWithTexture(context, exportButtonTexture);
//        FontRenderer.getInstance().renderText(context.getMatrices(), StyledText.fromComponent(Text.of("Export to clipboard")), x + (float) width / 2, y + height - 45f / scaleFactor, CustomColor.fromHexString("ffffff"), HorizontalAlignment.CENTER, VerticalAlignment.TOP, TextShadow.NORMAL, 2.75f / scaleFactor);


        FontRenderer.getInstance().renderText(context.getMatrices(), StyledText.fromComponent(Text.of("Opacity")), x + (float) (8 * 3) / scaleFactor, y + 200f / scaleFactor, CustomColor.fromHexString("ffffff"), HorizontalAlignment.LEFT, VerticalAlignment.MIDDLE, TextShadow.NORMAL, 3f / scaleFactor);
        alphaSlider.setX(x + width / 2 - 150 / scaleFactor);
        alphaSlider.setY(y + 190 / scaleFactor);
        alphaSlider.setHeight(20f / scaleFactor);
        alphaSlider.setWidth(300f / scaleFactor);
        alphaSlider.draw(context);

        FontRenderer.getInstance().renderText(context.getMatrices(), StyledText.fromComponent(Text.of("Enable/")), x + (float) (136 * 3) / scaleFactor, y + 25f * 3 / scaleFactor, CustomColor.fromHexString("ffffff"), HorizontalAlignment.LEFT, VerticalAlignment.MIDDLE, TextShadow.NORMAL, 2.75f / scaleFactor);
        FontRenderer.getInstance().renderText(context.getMatrices(), StyledText.fromComponent(Text.of("Disable")), x + (float) (136 * 3) / scaleFactor, y + 34f * 3 / scaleFactor, CustomColor.fromHexString("ffffff"), HorizontalAlignment.LEFT, VerticalAlignment.MIDDLE, TextShadow.NORMAL, 2.75f / scaleFactor);
        FontRenderer.getInstance().renderText(context.getMatrices(), StyledText.fromComponent(Text.of("Distance")), x + (float) (136 * 3) / scaleFactor, y + 43f * 3 / scaleFactor, CustomColor.fromHexString("ffffff"), HorizontalAlignment.LEFT, VerticalAlignment.MIDDLE, TextShadow.NORMAL, 2.75f / scaleFactor);
        FontRenderer.getInstance().renderText(context.getMatrices(), StyledText.fromComponent(Text.of("For all")), x + (float) (136 * 3) / scaleFactor, y + 52f * 3 / scaleFactor, CustomColor.fromHexString("ffffff"), HorizontalAlignment.LEFT, VerticalAlignment.MIDDLE, TextShadow.NORMAL, 2.75f / scaleFactor);

        colorPicker.setX(x + width - 15 * 3 / scaleFactor);
        colorPicker.setY(y + 25 * 3 / scaleFactor);
        colorPicker.setWidth(13f * 3 / scaleFactor);
        colorPicker.setHeight(13f * 3 / scaleFactor);
        colorPicker.draw(context);
        RenderUtils.drawTexturedRect(context.getMatrices(), colorPickerBorderTexture, x + width - 16f * 3 / scaleFactor, y + 24f * 3 / scaleFactor, 15f * 3/ scaleFactor, 15f * 3/ scaleFactor, 15 * 3/ scaleFactor, 15 * 3/ scaleFactor);
    }

    public void click(int mouseX, int mouseY) {
        if (hideAllBlocksButton.isClickInBounds(mouseX, mouseY)) {
            hideAllBlocksButton.click();
        }

        if (showAllBlocksButton.isClickInBounds(mouseX, mouseY)) {
            showAllBlocksButton.click();
        }

        if (hideAllNamesButton.isClickInBounds(mouseX, mouseY)) {
            hideAllNamesButton.click();
        }

        if (showAllNamesButton.isClickInBounds(mouseX, mouseY)) {
            showAllNamesButton.click();
        }

        if (hideAllDistancesButton.isClickInBounds(mouseX, mouseY)) {
            hideAllDistancesButton.click();
        }

        if (showAllDistancesButton.isClickInBounds(mouseX, mouseY)) {
            showAllDistancesButton.click();
        }

//        if(exportButton.isClickInBounds(mouseX, mouseY)) {
//            exportButton.click();
//        }

        if(nameInput.isClickInBounds(mouseX, mouseY)) {
            nameInput.click();
        } else {
            nameInput.setActive(false);
        }

        if(colorPicker.isClickInBounds(mouseX, mouseY)) {
            colorPicker.click(mouseX, mouseY);
        } else {
            colorPicker.expanded = false;
        }

        if(alphaSlider.isClickInBounds(mouseX, mouseY)) {
            alphaSlider.click();
        }
    }
}
