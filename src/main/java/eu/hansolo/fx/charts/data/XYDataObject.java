package eu.hansolo.fx.charts.data;

import eu.hansolo.fx.charts.Symbol;
import eu.hansolo.fx.charts.event.DataEvent;
import eu.hansolo.fx.charts.event.DataEventListener;
import eu.hansolo.fx.charts.event.SeriesEventListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.StringProperty;
import javafx.beans.property.StringPropertyBase;
import javafx.scene.paint.Color;

import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by hansolo on 16.07.17.
 */
public class XYDataObject implements XYData {
    private final DataEvent                         DATA_EVENT = new DataEvent(XYDataObject.this);
    private CopyOnWriteArrayList<DataEventListener> listeners;
    private double                                  _x;
    private DoubleProperty                          x;
    private double                                  _y;
    private DoubleProperty                          y;
    private String                                  _name;
    private StringProperty                          name;
    private Color                                   _color;
    private ObjectProperty<Color>                   color;
    private Symbol                                  _symbol;
    private ObjectProperty<Symbol>                  symbol;


    // ******************** Constructors **********************************
    public XYDataObject() {
        this(0, 0, "", Color.RED, Symbol.CIRCLE);
    }
    public XYDataObject(final double X, final double Y, final String NAME) {
        this(X, Y, NAME, Color.RED, Symbol.CIRCLE);
    }
    public XYDataObject(final double X, final double Y, final String NAME, final Color COLOR) {
        this(X, Y, NAME, COLOR, Symbol.CIRCLE);
    }
    public XYDataObject(final double X, final double Y, final String NAME, final Color COLOR, final Symbol SYMBOL) {
        _x        = X;
        _y        = Y;
        _name     = NAME;
        _color    = COLOR;
        _symbol   = SYMBOL;
        listeners = new CopyOnWriteArrayList<>();
    }


    // ******************** Methods ***************************************
    @Override public double getX() { return null == x ? _x : x.get(); }
    @Override public void setX(final double X) {
        if (null == x) {
            _x = X;
            fireDataEvent(DATA_EVENT);
        } else {
            x.set(X);
        }
    }
    @Override public DoubleProperty xProperty() {
        if (null == x) {
            x = new DoublePropertyBase(_x) {
                @Override protected void invalidated() { fireDataEvent(DATA_EVENT); }
                @Override public Object getBean() { return XYDataObject.this; }
                @Override public String getName() { return "x"; }
            };
        }
        return x;
    }

    @Override public double getY() { return null == y ? _y : y.get(); }
    @Override public void setY(final double Y) {
        if (null == y) {
            _y = Y;
            fireDataEvent(DATA_EVENT);
        } else {
            y.set(Y);
        }
    }
    @Override public DoubleProperty yProperty() {
        if (null == y) {
            y = new DoublePropertyBase(_y) {
                @Override protected void invalidated() { fireDataEvent(DATA_EVENT); }
                @Override public Object getBean() { return XYDataObject.this; }
                @Override public String getName() { return "y"; }
            };
        }
        return y;
    }

    @Override public String getName() { return null == name ? _name : name.get(); }
    public void setName(final String NAME) {
        if (null == name) {
            _name = NAME;
            fireDataEvent(DATA_EVENT);
        } else {
            name.set(NAME);
        }
    }
    public StringProperty nameProperty() {
        if (null == name) {
            name = new StringPropertyBase(_name) {
                @Override protected void invalidated() { fireDataEvent(DATA_EVENT); }
                @Override public Object getBean() { return XYDataObject.this; }
                @Override public String getName() { return "name"; }
            };
            _name = null;
        }
        return name;
    }

    @Override public Color getColor() { return null == color ? _color : color.get(); }
    public void setColor(final Color COLOR) {
        if (null == color) {
            _color = COLOR;
            fireDataEvent(DATA_EVENT);
        } else {
            color.set(COLOR);
        }
    }
    public ObjectProperty<Color> colorProperty() {
        if (null == color) {
            color = new ObjectPropertyBase<Color>(_color) {
                @Override protected void invalidated() { fireDataEvent(DATA_EVENT); }
                @Override public Object getBean() { return XYDataObject.this; }
                @Override public String getName() { return "color"; }
            };
            _color = null;
        }
        return color;
    }

    @Override public Symbol getSymbol() { return null == symbol ? _symbol : symbol.get(); }
    public void setSymbol(final Symbol SYMBOL) {
        if (null == symbol) {
            _symbol = SYMBOL;
            fireDataEvent(DATA_EVENT);
        } else {
            symbol.set(SYMBOL);
        }
    }
    public ObjectProperty<Symbol> symbolProperty() {
        if (null == symbol) {
            symbol = new ObjectPropertyBase<Symbol>(_symbol) {
                @Override protected void invalidated() { fireDataEvent(DATA_EVENT); }
                @Override public Object getBean() {  return XYDataObject.this;  }
                @Override public String getName() {  return "symbol";  }
            };
            _symbol = null;
        }
        return symbol;
    }

    
    // ******************** Event handling ************************************
    public void setOnDataEvent(final DataEventListener LISTENER) { addDataEventListener(LISTENER); }
    public void addDataEventListener(final DataEventListener LISTENER) { if (!listeners.contains(LISTENER)) listeners.add(LISTENER); }
    public void removeDataEventListener(final DataEventListener LISTENER) { if (listeners.contains(LISTENER)) listeners.remove(LISTENER); }

    public void fireDataEvent(final DataEvent EVENT) {
        for (DataEventListener listener : listeners) { listener.onDataEvent(EVENT); }
    }
    
    @Override public String toString() {
        return new StringBuilder().append("{\n")
                                  .append("  \"name\":\"").append(getName()).append("\",\n")
                                  .append("  \"x\":").append(getX()).append(",\n")
                                  .append("  \"y\":").append(getY()).append(",\n")
                                  .append("  \"color\":\"").append(getColor().toString().replace("0x", "#")).append("\",\n")
                                  .append("  \"symbol\":\"").append(getSymbol().name()).append("\"\n")
                                  .append("}")
                                  .toString();
    }
}
