/* This file was generated by SableCC (http://www.sable.mcgill.ca/sablecc/). */

package tudresden.ocl.parser.node;

import java.util.*;
import tudresden.ocl.parser.analysis.*;

public final class AClassifierContext extends PClassifierContext
{
    private PClassifierHead _classifierHead_;
    private PTypeName _typeName_;

    public AClassifierContext()
    {
    }

    public AClassifierContext(
        PClassifierHead _classifierHead_,
        PTypeName _typeName_)
    {
        setClassifierHead(_classifierHead_);

        setTypeName(_typeName_);

    }
    public Object clone()
    {
        return new AClassifierContext(
            (PClassifierHead) cloneNode(_classifierHead_),
            (PTypeName) cloneNode(_typeName_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAClassifierContext(this);
    }

    public PClassifierHead getClassifierHead()
    {
        return _classifierHead_;
    }

    public void setClassifierHead(PClassifierHead node)
    {
        if(_classifierHead_ != null)
        {
            _classifierHead_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _classifierHead_ = node;
    }

    public PTypeName getTypeName()
    {
        return _typeName_;
    }

    public void setTypeName(PTypeName node)
    {
        if(_typeName_ != null)
        {
            _typeName_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _typeName_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_classifierHead_)
            + toString(_typeName_);
    }

    void removeChild(Node child)
    {
        if(_classifierHead_ == child)
        {
            _classifierHead_ = null;
            return;
        }

        if(_typeName_ == child)
        {
            _typeName_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_classifierHead_ == oldChild)
        {
            setClassifierHead((PClassifierHead) newChild);
            return;
        }

        if(_typeName_ == oldChild)
        {
            setTypeName((PTypeName) newChild);
            return;
        }

    }
}

