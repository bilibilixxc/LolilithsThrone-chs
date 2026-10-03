package com.lilithsthrone.LolificationProject.Experiments;

import com.lilithsthrone.LolificationProject.ModCommon.Logger;
import com.ximpleware.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Consumer;

public class XMLLoader {
    public boolean debugMode = false;
    VTDNav vNav;

    public static XMLLoader load(File xmlFile) { return new XMLLoader(xmlFile, false); }
    public static XMLLoader load(File xmlFile, boolean debug) { return new XMLLoader(xmlFile, debug); }
    public XMLLoader(File xmlFile, boolean debug) {
        VTDGen vGen = new VTDGen();
        try {
            byte[] xmlBytes = Files.readAllBytes(xmlFile.toPath());
            vGen.setDoc(xmlBytes);
            vGen.parse(true);
            vNav = vGen.getNav();
        } catch (FileNotFoundException e) {
            if (debugMode) Logger.logTest("Failed to read file : " + xmlFile.getPath());
            e.printStackTrace();
        } catch (EOFException e) {
            if (debugMode) Logger.logTest("Premature End of File File at Fault : " + xmlFile.getPath());
            e.printStackTrace();
        } catch (EntityException e) {
            if (debugMode) Logger.logTest("VTD Entity Exception File at Fault : " + xmlFile.getPath());
            e.printStackTrace();
        } catch (IOException e) {
            if (debugMode) Logger.logTest("IO Error in File : " + xmlFile.getPath());
            e.printStackTrace();
        } catch (EncodingException e) {
            if (debugMode) Logger.logTest("Encoding Error in File : " + xmlFile.getPath());
            e.printStackTrace();
        } catch (ParseException e) {
            if (debugMode) Logger.logTest("Parsing Error for File : " + xmlFile.getPath());
            e.printStackTrace();
        }
        debugMode = debug;
    }
    public XMLLoader(VTDNav vtdNav) {
        vNav = vtdNav;
    }

    /** Looks for and goes to node, if it's directly under current element, or anywhere under the current parent */
    public boolean moveToNode(String elementName) { return moveToNode(elementName, false); }

    public boolean moveToNode(String elementName, boolean forwardOnly) {
        try {
            if (vNav.toElement(VTDNav.FIRST_CHILD, elementName)) {
                if (debugMode) Logger.logTest("Moved to child @[" + elementName + "]");
                return true;
            } else if (vNav.toElement(VTDNav.NEXT_SIBLING, elementName)) {
                if (debugMode) Logger.logTest("Moved to Next sibling @[" + elementName + "]");
                return true;
            } else if (!forwardOnly && vNav.toElement(VTDNav.PREV_SIBLING, elementName)) {
                if (debugMode) Logger.logTest("Moved to Prev sibling @[" + elementName + "]");
                return true;
            }
            logLocation("Cannot Find sibling/child @[" + elementName + "]", "");
        } catch (NavException e) {
            if (debugMode) Logger.logTest("Failed NAV");
            e.printStackTrace();
        }

        return false;
    }

    public void goToParent() {
        try {
            vNav.toElement(VTDNav.PARENT);
            if (debugMode) logLocation("Went to Parent Node", "");
        } catch (NavException e) {
            if (debugMode) logLocation("There is no parent", "");
            e.printStackTrace();
        }
    }

    /** gets attribute 'value' from supplied element as a String, with a default
     * @param elementName The element to request the string value of the 'value' attribute.
     * @return An empty string if there is no 'value' attribute at provided element
     * */
    public String getElementAttributeAsStr(String elementName) {
        return getElementAttributeAsStr(elementName, "value");
    }
    /** gets the attribute value from supplied element as a String
     * @param elementName The element to request the string value of the supplied attribute.
     * @param attributeTag The attribute tag to request the value of
     * @return An empty string if there is no attributeTag data is at provided element
     * */
    public String getElementAttributeAsStr(String elementName, String attributeTag) {
        return getElementAttributeAsStr(elementName, attributeTag, "");
    }
    /** gets the attribute value from supplied element as a String or default value
     * @param elementName The element to request the string value of the supplied attribute.
     * @param attributeTag The attribute tag to request the value of
     * @param defaultValue A supplied default value if attribute is not found
     * @return The requested attribute value, at supplied location, or defaultValue
     * */
    public String getElementAttributeAsStr(String elementName, String attributeTag, String defaultValue) {
        try {
            if (vNav.toElement(VTDNav.FIRST_CHILD, elementName)) {
                if (debugMode)
                    Logger.logTest("Getting : #[" + attributeTag + "] From @[" + elementName + "]");
                String value = getCurrentElementAttributeAsStr(attributeTag);
                vNav.toElement(VTDNav.PARENT);
                return value;
            }
        } catch (NavException e) {
            if (debugMode) logLocation("NavPoint Error for elementName : " + elementName, "");
            e.printStackTrace();
        }
        return defaultValue;
    }

    /** gets attribute 'value' from supplied element as an Integer, with a default value of 0
     * @param elementName The element to request the integer value of the 'value' attribute.
     * @return 0 if there is no 'value' attribute at provided element otherwise the integer found
     * */
    public Integer getElementAttributeAsInt(String elementName) {
        return getElementAttributeAsInt(elementName, "value", 0);
    }

    /** gets the attribute value from supplied element as a Integer
     * @param elementName The element to request the Integer value of the supplied attribute.
     * @param defaultValue A supplied default value if attribute is not found
     * @return defaultValue if there is no 'value' attribute is at provided element
     * */
    public Integer getElementAttributeAsInt(String elementName, int defaultValue) {
        return getElementAttributeAsInt(elementName, "value", defaultValue);
    }
    /** gets the attribute value from supplied element as a Integer
     * @param elementName The element to request the Integer value of the supplied attribute.
     * @param attributeTag The attribute tag to request the value of
     * @return 0 if there is no attributeTag data is at provided element
     * */
    public Integer getElementAttributeAsInt(String elementName, String attributeTag) {
        return getElementAttributeAsInt(elementName, attributeTag, 0);
    }
    /** gets the attribute value from supplied element as a Integer or default value
     * @param elementName The element to request the Integer value of the supplied attribute.
     * @param attributeTag The attribute tag to request the value of
     * @param defaultValue A supplied default value if attribute is not found
     * @return The requested attribute value, at supplied location, or defaultValue
     * */
    public Integer getElementAttributeAsInt(String elementName, String attributeTag, int defaultValue) {
        String val = getElementAttributeAsStr(elementName, attributeTag);
        if (val.isEmpty()) return defaultValue;
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            if (debugMode) Logger.logTest("Error Getting Value for elementName : " + elementName + " value : " + val);
            e.printStackTrace();
        }
        return defaultValue;
    }

    public Long getElementAttributeAsLong(String elementName) {
        return getElementAttributeAsLong(elementName, "value", 0L);
    }
    public Long getElementAttributeAsLong(String elementName, long defaultValue) {
        return getElementAttributeAsLong(elementName, "value", defaultValue);
    }
    public Long getElementAttributeAsLong(String elementName, String attributeTag) {
        return getElementAttributeAsLong(elementName, attributeTag, 0L);
    }
    public Long getElementAttributeAsLong(String elementName, String attributeTag, long defaultValue) {
        String val = getElementAttributeAsStr(elementName, attributeTag);
        if (val.isEmpty()) return defaultValue;
        try {
            return Long.parseLong(val);
        } catch (NumberFormatException e) {
            if (debugMode) Logger.logTest("Error Getting Value for elementName : " + elementName + " value : " + val);
            e.printStackTrace();
        }
        return defaultValue;
    }


    public Float getElementAttributeAsFloat(String elementName) {
        return getElementAttributeAsFloat(elementName, "value", 0f);
    }
    public Float getElementAttributeAsFloat(String elementName, float defaultValue) {
        return getElementAttributeAsFloat(elementName, "value", defaultValue);
    }
    public Float getElementAttributeAsFloat(String elementName, String attributeTag) {
        return getElementAttributeAsFloat(elementName, attributeTag, 0f);
    }
    public Float getElementAttributeAsFloat(String elementName, String attributeTag, float defaultValue) {
        String val = getElementAttributeAsStr(elementName, attributeTag);
        if (val.isEmpty()) return defaultValue;
        try {
            return Float.parseFloat(val);
        } catch (NumberFormatException e) {
            if (debugMode) Logger.logTest("Error Getting Value for elementName : " + elementName + " value : " + val);
            e.printStackTrace();
        }
        return defaultValue;
    }
    public Boolean getElementAttributeAsBoolean(String elementName) {
        return getElementAttributeAsBoolean(elementName, "value", false);
    }
    public Boolean getElementAttributeAsBoolean(String elementName, boolean defaultValue) {
        return getElementAttributeAsBoolean(elementName, "value", defaultValue);
    }
    public Boolean getElementAttributeAsBoolean(String elementName, String attributeTag) {
        return getElementAttributeAsBoolean(elementName, attributeTag, false);
    }
    public Boolean getElementAttributeAsBoolean(String elementName, String attributeTag, boolean defaultValue) {
        String val = getElementAttributeAsStr(elementName, attributeTag);
        if (val.isEmpty()) return defaultValue;
        try {
            return Boolean.parseBoolean(val);
        } catch (NumberFormatException e) {
            if (debugMode) Logger.logTest("Error Getting Value for elementName : " + elementName + " value : " + val);
            e.printStackTrace();
        }
        return defaultValue;
    }
    public int getNextElementAttributeAsInt(String elementName) {
        return getNextElementAttributeAsInt(elementName, "value");
    }
    public int getNextElementAttributeAsInt(String elementName, String attributeTag) {
        return getNextElementAttributeAsInt(elementName, attributeTag, 0);
    }
    public int getNextElementAttributeAsInt(String elementName, String attributeTag, int defaultValue) {
        String val = getNextElementAttributeAsStr(elementName, attributeTag);
        if (val.isEmpty()) return defaultValue;
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            if (debugMode) Logger.logTest("Failed to parse [" + val + "] as a number");
        }
        return defaultValue;
    }
    public String getNextElementAttributeAsStr(String elementName) {
        return getNextElementAttributeAsStr(elementName, "value");
    }
    public String getNextElementAttributeAsStr(String elementName, String attributeTag) {
        return getNextElementAttributeAsStr(elementName, attributeTag, "");
    }
    public String getNextElementAttributeAsStr(String elementName, String attributeTag, String defaultValue) {
        try {
            if (vNav.toElement(VTDNav.FIRST_CHILD, elementName)) {
                logLocation("Getting First Child String", "");
                return getCurrentElementAttributeAsStr(attributeTag, defaultValue);
            } else if (vNav.toElement(VTDNav.NEXT_SIBLING, elementName)) {
                logLocation("Getting Next Sibling String", "");
                return getCurrentElementAttributeAsStr(attributeTag, defaultValue);
            }
        } catch (NavException e) {
            if (debugMode)
                Logger.logTest("NavPoint Error for elementName : " + elementName);
            e.printStackTrace();
        }
        logLocation("Failed to find next Element Returning Default Value [" + defaultValue + "]", attributeTag);
        return defaultValue;
    }
    
    /** Gets attributeTag value at current nav point */
    public String getCurrentElementAttributeAsStr(String attributeTag) {
        return getCurrentElementAttributeAsStr(attributeTag, "");
    }
    /** Gets attributeTag value at current nav point */
    public String getCurrentElementAttributeAsStr(String attributeTag, String defaultValue) {
        try {
            int navPoint = vNav.getAttrVal(attributeTag);
            if (navPoint == -1) {
                logLocation("Failed getCurrentElement", attributeTag);
                return defaultValue;
            }
            String returns = vNav.toNormalizedString(navPoint);
            logLocation("Returning [ " + returns + " ] from #[" + attributeTag + "]", "");
            return returns;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
    /** Gets attributeTag value at current nav point */
    public Integer getCurrentElementAttributeAsInt(String attributeTag) {
        return getCurrentElementAttributeAsInt(attributeTag, 0);
    }

    public Integer getCurrentElementAttributeAsInt(String attributeTag, int defaultValue) {
        String value = getCurrentElementAttributeAsStr(attributeTag);
        if (value.isEmpty()) return defaultValue;
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            if (debugMode) Logger.logTest("Failed to parse integer from : " + value + " retrieved using tag : " + attributeTag);
            e.printStackTrace();
        }
        return defaultValue;
    }

    /** Gets attributeTag value at current nav point */
    public Long getCurrentElementAttributeAsLong(String attributeTag) {
        return getCurrentElementAttributeAsLong(attributeTag, 0L);
    }
    public Long getCurrentElementAttributeAsLong(String attributeTag, long defaultValue) {
        String value = getCurrentElementAttributeAsStr(attributeTag);
        if (value.isEmpty()) return defaultValue;
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            if (debugMode) Logger.logTest("Failed to parse integer from : " + value + " retrieved using tag : " + attributeTag);
            e.printStackTrace();
        }
        return defaultValue;
    }
    /** Gets attributeTag value at current nav point */
    public Float getCurrentElementAttributeAsFloat(String attributeTag) {
        return getCurrentElementAttributeAsFloat(attributeTag, 0f);
    }
    public Float getCurrentElementAttributeAsFloat(String attributeTag, float defaultValue) {
        String value = getCurrentElementAttributeAsStr(attributeTag);
        if (value.isEmpty()) return defaultValue;
        try {
            return Float.parseFloat(value);
        } catch (Exception e) {
            if (debugMode) Logger.logTest("Failed to parse float from : " + value + " retrieved using tag : " + attributeTag);
            e.printStackTrace();
        }
        return defaultValue;
    }
    /** Gets attributeTag value at current nav point */
    public Boolean getCurrentElementAttributeAsBoolean(String attributeTag) {
        return getCurrentElementAttributeAsBoolean(attributeTag, false);
    }
    public Boolean getCurrentElementAttributeAsBoolean(String attributeTag, boolean defaultValue) {
        String value = getCurrentElementAttributeAsStr(attributeTag);
        try {
            return Boolean.parseBoolean(value);
        } catch (Exception e) {
            if (debugMode) Logger.logTest("Failed to parse integer from : " + value + " retrieved using tag : " + attributeTag);
            e.printStackTrace();
        }
        return defaultValue;
    }

    public String getCurrentElementText() {
        try {
            int navPoint = vNav.getText();
            if (navPoint == -1) {
                logLocation("Failed to get current Element's Text", "");
            } else {
                logLocation("Got Current Element's Text", "");
            }
            return vNav.toNormalizedString(navPoint);
        } catch (Exception e) {
            System.err.println("getCurrentVal failed @ " + getCurrentLocation() + "index: " + vNav.getCurrentIndex() + ", Depth:" + vNav.getCurrentDepth());
            e.printStackTrace();
        }
        logLocation("Current Text doesn't exist", "");
        return "";
    }

    public float getCurrentElementFloat() {
        String val = getCurrentElementText();
        if (val.isEmpty()) return 0f;
        try {
            return Float.parseFloat(val);
        } catch (Exception e) {
            Logger.logTest("Failed to read : [" + val + "] as a float");
        }
        return 0f;
    }

    public int getCurrentElementInt() {
        String val = getCurrentElementText();
        if (val.isEmpty()) return 0;
        try {
            return Integer.parseInt(val);
        } catch (Exception e) {
            Logger.logTest("Failed to read : [" + val + "] as a int");
        }
        return 0;
    }

    public long getCurrentElementLong() {
        String val = getCurrentElementText();
        if (val.isEmpty()) return 0L;
        try {
            return Long.parseLong(val);
        } catch (Exception e) {
            Logger.logTest("Failed to read : [" + val + "] as a long");
        }
        return 0L;
    }

    public boolean getCurrentElementBoolean() {
        String val = getCurrentElementText();
        if (val.isEmpty()) return false;
        try {
            return Boolean.parseBoolean(val);
        } catch (Exception e) {
            Logger.logTest("Failed to read : [" + val + "] as a boolean");
        }
        return false;
    }

    public void close() { vNav = null; }

    public boolean nodeExists(String elementName) {
        try {
            if (vNav.toElement(VTDNav.FIRST_CHILD, elementName)) {
                logLocation("Node Exists :", "");
                vNav.toElement(VTDNav.PARENT);
                return true;
            } else if (vNav.toElement(VTDNav.NEXT_SIBLING, elementName)) {
                logLocation("Node Exists as Next sibling", "");
                vNav.toElement(VTDNav.PARENT);
                return true;
            } else if (vNav.toElement(VTDNav.PREV_SIBLING, elementName)) {
                logLocation("Node Exists as Prev sibling", "");
                vNav.toElement(VTDNav.PARENT);
                return true;
            }
            logLocation("Cannot Find : @[" + elementName + "]", "");
        } catch (NavException e) {
            if (debugMode) Logger.logTest("Failed NAV");
            e.printStackTrace();
        }
        return false;
    }

    public boolean moveToIfHasChildren(String elementName) {
        if (hasChildren(elementName)) {
            return moveToNode(elementName);
        }
        return false;
    }

    public void forEachAttributeOfElement(String elementName, String attributeName, Consumer<String> action) {
        String attVal;
        boolean found = false;
        while (!(attVal = getNextElementAttributeAsStr(elementName, attributeName)).isEmpty()) {
            found = true;
            action.accept(attVal);
        }

        if (found) goToParent();
    }

    public void forEachAttributeIntOfElement(String elementName, String attributeName, Consumer<Integer> action) {
        String attVal;
        boolean found = false;
        while (!(attVal = getNextElementAttributeAsStr(elementName, attributeName)).isEmpty()) {
            found = true;
            int value = Integer.parseInt(attVal);
            action.accept(value);
        }
        if (found) goToParent();
    }

    public void forEachTextContent(String elementName, Consumer<String> action) {
        boolean found = false;
        while (moveToNode(elementName, true)) {
            found = true;
            action.accept(getCurrentElementText());
        }
        if (found) goToParent();
    }

    public void forEachTextContentAsInt(String elementName, Consumer<Integer> action) {
        boolean found = false;
        while (moveToNode(elementName, true)) {
            found = true;
            action.accept(getCurrentElementInt());
        }
        if (found) goToParent();
    }

    public void forEachTextContentAsBoolean(String elementName, Consumer<Boolean> action) {
        boolean found = false;
        while (moveToNode(elementName, true)) {
            found = true;
            action.accept(getCurrentElementBoolean());
        }
        if (found) goToParent();
    }

    public void forEachTextContentAsLong(String elementName, Consumer<Long> action) {
        boolean found = false;
        while (moveToNode(elementName, true)) {
            found = true;
            action.accept(getCurrentElementLong());
        }
        if (found) goToParent();
    }

    public void forEachTextContentAsFloat(String elementName, Consumer<Float> action) {
        boolean found = false;
        while (moveToNode(elementName, true)) {
            found = true;
            action.accept(getCurrentElementFloat());
        }
        if (found) goToParent();
    }

    public boolean hasChildren(String elementName) {
        vNav.push();
        boolean childrenFound = false;
        if (moveToNodeQuietly(elementName, true)) {
            try {
                if (vNav.toElement(VTDNav.FIRST_CHILD))
                    childrenFound = true;
            } catch (Exception ignored) {}
        }
        vNav.pop();
        if (debugMode) Logger.logTest("@[" + elementName + "] Has Children : " + (childrenFound ? "Yes" : "No"));
        return childrenFound;
    }

    public boolean moveToNodeQuietly(String elementName, boolean forwardOnly) {
        try {
            if (vNav.toElement(VTDNav.FIRST_CHILD, elementName)) {
                return true;
            } else if (vNav.toElement(VTDNav.NEXT_SIBLING, elementName)) {
                return true;
            } else if (!forwardOnly && vNav.toElement(VTDNav.PREV_SIBLING, elementName)) {
                return true;
            }
        } catch (NavException ignored) {}
        return false;
    }

    public boolean currentElementHasChildren() {
        vNav.push();
        boolean childrenFound = false;
        try {
            if (vNav.toElement(VTDNav.FIRST_CHILD))
                childrenFound = true;
        } catch (Exception ignored) {}
        vNav.pop();
        return childrenFound;
    }

    private void logLocation(String prefix, String attributeTag) {
        if (!debugMode) return;
        try {
            if (attributeTag.isEmpty())
                Logger.logTest(prefix + " @[" + getCurrentLocation() + "]"); //  index: " + vNav.getCurrentIndex() + ", Depth:" + vNav.getCurrentDepth()
            else Logger.logTest(prefix + " Cannot Find attr: #[" + attributeTag + "] @[" + getCurrentLocation() + "]");
            //index: " + vNav.getCurrentIndex() + ", Depth: " + vNav.getCurrentDepth()
        } catch (Exception e) {e.printStackTrace();}
    }

    public String getCurrentLocation() {
        try {
            return vNav.toNormalizedString(vNav.getCurrentIndex());
        } catch (NavException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void setDebugMode(boolean debug) {
        if (debug == debugMode) return;
        Logger.logTest("Debug Mode " + (debug ? "Enabled" : "Disabled"));
        debugMode = debug;
    }

    public XMLLoader clone() {
        return new XMLLoader(vNav.cloneNav());
    }

    public void pushLocation() {
        vNav.push();
    }

    public void popLocation() {
        vNav.pop();
    }
}
