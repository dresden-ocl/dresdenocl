/* This file was generated by SableCC (http://www.sable.mcgill.ca/sablecc/). */

package tudresden.ocl.parser.node;

import java.util.*;
import tudresden.ocl.parser.analysis.*;

public final class AMultMultiplyOperator extends PMultiplyOperator
{
    private TMult _mult_;

    public AMultMultiplyOperator()
    {
    }

    public AMultMultiplyOperator(
        TMult _mult_)
    {
        setMult(_mult_);

    }
    public Object clone()
    {
        return new AMultMultiplyOperator(
            (TMult) cloneNode(_mult_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMultMultiplyOperator(this);
    }

    public TMult getMult()
    {
        return _mult_;
    }

    public void setMult(TMult node)
    {
        if(_mult_ != null)
        {
            _mult_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _mult_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_mult_);
    }

    void removeChild(Node child)
    {
        if(_mult_ == child)
        {
            _mult_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_mult_ == oldChild)
        {
            setMult((TMult) newChild);
            return;
        }

    }
}

