/* This file was generated by SableCC (http://www.sable.mcgill.ca/sablecc/). */

package tudresden.ocl.parser.node;

import java.util.*;
import tudresden.ocl.parser.analysis.*;

public final class ACollectionCollectionType extends PCollectionType
{
    private TTCollection _tCollection_;

    public ACollectionCollectionType()
    {
    }

    public ACollectionCollectionType(
        TTCollection _tCollection_)
    {
        setTCollection(_tCollection_);

    }
    public Object clone()
    {
        return new ACollectionCollectionType(
            (TTCollection) cloneNode(_tCollection_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACollectionCollectionType(this);
    }

    public TTCollection getTCollection()
    {
        return _tCollection_;
    }

    public void setTCollection(TTCollection node)
    {
        if(_tCollection_ != null)
        {
            _tCollection_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _tCollection_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_tCollection_);
    }

    void removeChild(Node child)
    {
        if(_tCollection_ == child)
        {
            _tCollection_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_tCollection_ == oldChild)
        {
            setTCollection((TTCollection) newChild);
            return;
        }

    }
}

