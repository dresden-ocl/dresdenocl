/* This file was generated by SableCC (http://www.sable.mcgill.ca/sablecc/). */

package tudresden.ocl.parser.node;

import java.util.*;
import tudresden.ocl.parser.analysis.*;

public final class AActualParameterListTail extends PActualParameterListTail
{
    private TComma _comma_;
    private PExpression _expression_;

    public AActualParameterListTail()
    {
    }

    public AActualParameterListTail(
        TComma _comma_,
        PExpression _expression_)
    {
        setComma(_comma_);

        setExpression(_expression_);

    }
    public Object clone()
    {
        return new AActualParameterListTail(
            (TComma) cloneNode(_comma_),
            (PExpression) cloneNode(_expression_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAActualParameterListTail(this);
    }

    public TComma getComma()
    {
        return _comma_;
    }

    public void setComma(TComma node)
    {
        if(_comma_ != null)
        {
            _comma_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _comma_ = node;
    }

    public PExpression getExpression()
    {
        return _expression_;
    }

    public void setExpression(PExpression node)
    {
        if(_expression_ != null)
        {
            _expression_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _expression_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_comma_)
            + toString(_expression_);
    }

    void removeChild(Node child)
    {
        if(_comma_ == child)
        {
            _comma_ = null;
            return;
        }

        if(_expression_ == child)
        {
            _expression_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_comma_ == oldChild)
        {
            setComma((TComma) newChild);
            return;
        }

        if(_expression_ == oldChild)
        {
            setExpression((PExpression) newChild);
            return;
        }

    }
}

