/* This file was generated by SableCC (http://www.sablecc.org/). */

package tudresden.ocl.codegen.decl.treegen.node;

import java.util.*;
import tudresden.ocl.codegen.decl.treegen.analysis.*;

public final class AExistsBooleanExpression extends PBooleanExpression
{
    private PQueryExpression _queryExpression_;

    public AExistsBooleanExpression()
    {
    }

    public AExistsBooleanExpression(
        PQueryExpression _queryExpression_)
    {
        setQueryExpression(_queryExpression_);

    }
    public Object clone()
    {
        return new AExistsBooleanExpression(
            (PQueryExpression) cloneNode(_queryExpression_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAExistsBooleanExpression(this);
    }

    public PQueryExpression getQueryExpression()
    {
        return _queryExpression_;
    }

    public void setQueryExpression(PQueryExpression node)
    {
        if(_queryExpression_ != null)
        {
            _queryExpression_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _queryExpression_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_queryExpression_);
    }

    void removeChild(Node child)
    {
        if(_queryExpression_ == child)
        {
            _queryExpression_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_queryExpression_ == oldChild)
        {
            setQueryExpression((PQueryExpression) newChild);
            return;
        }

    }
}
