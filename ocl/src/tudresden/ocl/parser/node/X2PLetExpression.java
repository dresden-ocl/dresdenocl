/* This file was generated by SableCC (http://www.sable.mcgill.ca/sablecc/). */

package tudresden.ocl.parser.node;

import tudresden.ocl.parser.analysis.*;

public final class X2PLetExpression extends XPLetExpression
{
    private PLetExpression _pLetExpression_;

    public X2PLetExpression()
    {
    }

    public X2PLetExpression(
        PLetExpression _pLetExpression_)
    {
        setPLetExpression(_pLetExpression_);
    }

    public Object clone()
    {
        throw new RuntimeException("Unsupported Operation");
    }

    public void apply(Switch sw)
    {
        throw new RuntimeException("Switch not supported.");
    }

    public PLetExpression getPLetExpression()
    {
        return _pLetExpression_;
    }

    public void setPLetExpression(PLetExpression node)
    {
        if(_pLetExpression_ != null)
        {
            _pLetExpression_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _pLetExpression_ = node;
    }

    void removeChild(Node child)
    {
        if(_pLetExpression_ == child)
        {
            _pLetExpression_ = null;
        }
    }

    void replaceChild(Node oldChild, Node newChild)
    {
    }

    public String toString()
    {
        return "" +
            toString(_pLetExpression_);
    }
}

