package Graph

import collection.JavaConverters._
import info.rmarcus.ggen4j.graph.{GGenGraph, Vertex}
import info.rmarcus.ggen4j.StaticGraphGenerator

object GGenWrapper {
  def getSources(): List[Vertex] = {
    val graphGenerator = new StaticGraphGenerator()
    val graph: GGenGraph = graphGenerator.poisson2D(20, 5).generateGraph()

    graph.getSources().asScala.toList
  }
}
