package pl.edu.pw.elka.tabusearch.domain;

import java.util.Objects;

public class Edge
{
    private final Node first;
    private final Node second;
    private final Integer weight;

    public Edge(final Node first, final Node second, final Integer weight)
    {
        this.first = first;
        this.second = second;
        this.weight = weight;
    }

    public Node getFirst()
    {
        return first;
    }

    public Node getSecond()
    {
        return second;
    }

    public Integer getWeight()
    {
        return weight;
    }

    @Override
    public int hashCode()
    {
        //edges (A,B) and (B,A) are equal so they should have the same hash code
        return first.hashCode() + second.hashCode();
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        //edges (A,B) and (B,A) are equal (non-directed graph)
        final Edge other = (Edge) obj;
        return (Objects.equals(this.first, other.first) && Objects.equals(this.second, other.second))
                || (Objects.equals(this.first, other.second) && Objects.equals(this.second, other.first));
    }
}
