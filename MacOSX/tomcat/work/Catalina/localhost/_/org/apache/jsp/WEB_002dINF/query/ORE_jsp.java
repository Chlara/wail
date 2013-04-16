/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.30
 * Generated at: 2013-03-19 21:45:57 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.query;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Date;
import org.archive.wayback.core.UIResults;
import org.archive.wayback.util.StringFormatter;
import org.archive.wayback.core.WaybackRequest;
import org.archive.wayback.core.CaptureSearchResults;
import org.archive.wayback.core.CaptureSearchResult;
import org.archive.wayback.ResultURIConverter;
import java.text.SimpleDateFormat;
import org.archive.wayback.util.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import org.dspace.foresite.Aggregation;
import org.dspace.foresite.ResourceMap;
import org.dspace.foresite.Agent;
import org.dspace.foresite.OREFactory;
import org.dspace.foresite.AggregatedResource;
import org.dspace.foresite.ORESerialiser;
import org.dspace.foresite.ORESerialiserFactory;
import org.dspace.foresite.ResourceMapDocument;
import java.io.PrintWriter;
import java.net.URI;
import org.dspace.foresite.Predicate;
import org.archive.wayback.archivalurl.ArchivalUrlResultURIConverter;
import org.dspace.foresite.Triple;
import org.dspace.foresite.jena.TripleJena;
import java.util.UUID;
import java.util.TimeZone;
import java.util.Calendar;

public final class ORE_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;


	UIResults results = UIResults.extractCaptureQuery(request);//nuzno potom perepisat'
	SimpleDateFormat httpformatterl = new SimpleDateFormat(
			"E, dd MMM yyyy HH:mm:ss z");
	TimeZone tzo = TimeZone.getTimeZone("GMT");
	httpformatterl.setTimeZone(tzo);

	WaybackRequest wbRequest = results.getWbRequest();
	CaptureSearchResults cResults = results.getCaptureResults();
	CaptureSearchResult res = cResults.getClosest();

    String replayPrefix = wbRequest.getAccessPoint().getReplayPrefix();
    String queryPrefix = wbRequest.getAccessPoint().getQueryPrefix();
	String u = wbRequest.getRequestUrl();
	String agguri = replayPrefix + "timebundle/" + u;
	String format = wbRequest.get("format");
	if(format == null) {
		format = "rdf";
	}
	Aggregation agg = OREFactory.createAggregation(new URI(agguri));
	ResourceMap rem = agg.createResourceMap(new URI(queryPrefix
			+ "timemap/" + format + "/" + u));

	Date now = new Date();

	rem.setCreated(now);
	Predicate pr_type = new Predicate();
	pr_type.setURI(new URI(
			"http://www.w3.org/1999/02/22-rdf-syntax-ns#type"));

	rem.setModified(now);
	rem.createTriple(pr_type, new URI(
			"http://www.mementoweb.org/terms/tb/TimeMap"));
	Agent creator = OREFactory.createAgent();
	creator.addName("Foresite Toolkit (Java)");

	rem.addCreator(creator);
	agg.addTitle("Memento Time Bundle for " + u);

	Iterator<CaptureSearchResult> itr = cResults.iterator();
	SimpleDateFormat formatterk = new SimpleDateFormat("yyyyMMddHHmmss");
	formatterk.setTimeZone(tzo);
	Date f = cResults.getFirstResultDate();
	Date l = cResults.getLastResultDate();

	String ArchiveInterval = formatterk.format(f) + " - "
			+ formatterk.format(l);

	agg.addType(new URI("http://www.mementoweb.org/terms/tb/TimeBundle"));
	//include original into aggregation

	AggregatedResource ar_o = agg.createAggregatedResource(new URI(u));
	ar_o.createTriple(pr_type, new URI(
			"http://www.mementoweb.org/terms/tb/OriginalResource"));
	//include timegate into aggregation
	AggregatedResource ar_tg = agg.createAggregatedResource(new URI(
			replayPrefix + "timegate/" + u));

	Predicate pr_format = new Predicate();
	pr_format.setURI(new URI("http://purl.org/dc/elements/1.1/format"));
	ar_tg.createTriple(pr_format, new URI(u));
	ar_tg.createTriple(pr_type, new URI(
			"http://www.mementoweb.org/terms/tb/TimeGate"));

	String previos_digest = null;
	List<String> previos_blancs = new ArrayList<String>();

	Predicate pr = new Predicate();
	pr.setURI(new URI("http://www.mementoweb.org/terms/tb/start"));
	Predicate pre = new Predicate();
	pre.setURI(new URI("http://www.mementoweb.org/terms/tb/end"));
	Calendar cal = Calendar.getInstance();
	AggregatedResource ar = null;

	Date enddate = null;

	// String buffer for special link serialization format
	StringBuffer linkbf = new StringBuffer();

	linkbf.append("<" + u + ">;rel=\"original\"\n");
	linkbf.append(",<" + agguri + ">;rel=\"timebundle\"\n");
	linkbf.append(",<" + replayPrefix
			+ "timegate/" + u + ">;rel=\"timegate\"\n");
	linkbf.append(",<" + queryPrefix + "timemap/" + format + "/" + u
			+ ">;rel=\"timemap\";type=\"application/link-format\"\n");

	String firstmemento = null;
	int count = 0;
	while (itr.hasNext()) {
		CaptureSearchResult cur = itr.next();
		//I am not deduping urls (by digest) for the rdf serialization running out of time, extra efforts for me now ;)

		String resurl = replayPrefix
				+ formatterk.format(cur.getCaptureDate()) + "/" + u;

		String digest = cur.getDigest();
		if (previos_digest == null) {
			previos_digest = digest;
		}

		ar = agg.createAggregatedResource(new URI(resurl));
		ar.createTriple(pr_format, cur.getMimeType());

		Predicate pr_1 = new Predicate();
		pr_1.setURI(new URI(
				"http://www.mementoweb.org/terms/tb/mementoFor"));
		ar.createTriple(pr_1, new URI(u));
		ar.createTriple(pr_type, new URI(
				"http://www.mementoweb.org/terms/tb/Memento"));

		Date startdate = cur.getDuplicateDigestStoredDate();
		enddate = cur.getCaptureDate();

		// serialiase it in links format only for unique  digest

		if (startdate == null) {
			if (firstmemento == null) {
				linkbf.append(",<" + resurl
						+ ">;rel=\"first memento\";datetime=\""
						+ httpformatterl.format(enddate) + "\"\n");
				firstmemento = "firstmemento";

			} else {
				linkbf.append(",<" + resurl
						+ ">;rel=\"memento\";datetime=\""
						+ httpformatterl.format(enddate) + "\"\n");
				count = count + 1;
			}
		}

		// Adding blanc node
		Triple triple = new TripleJena();
		triple.initialise(new URI(resurl));
		Predicate pred = new Predicate();
		UUID a = UUID.randomUUID();
		String blanc = "urn:uuid:" + a.toString();

		pred.setURI(new URI(
				"http://www.mementoweb.org/terms/tb/observedOver"));
		triple.relate(pred, new URI(blanc));
		Triple tr = new TripleJena();
		tr.initialise(new URI(blanc));

		tr.relate(pr_type, new URI(
				"http://www.mementoweb.org/terms/tb/Period"));

		//period difined by [ [ interval [ date first digest recorded  and date of next digest recorded [ 

		String start = null;
		Triple trd = new TripleJena();
		trd.initialise(new URI(blanc));

		if (startdate != null) {

			cal.setTime(startdate);
			trd.relate(pr, cal);
			start = httpformatterl.format(startdate);
		} else {
			cal.setTime(enddate);
			trd.relate(pr, cal);
			start = httpformatterl.format(enddate);
		}

		ar.addTriple(triple);
		ar.addTriple(tr);
		ar.addTriple(trd);

		if (!digest.equals("previos_digest")) {

			Iterator<String> it = previos_blancs.iterator();
			while (it.hasNext()) {
				String blanc_ = (String) it.next();
				Triple tre = new TripleJena();
				tre.initialise(new URI(blanc_));

				cal.setTime(enddate);
				tre.relate(pre, cal);
				ar.addTriple(tre);
			}

			previos_blancs.clear();
			previos_digest = digest;
		}

		previos_blancs.add(blanc);

	}

	Iterator it = previos_blancs.iterator();
	while (it.hasNext()) {
		String blanc_ = (String) it.next();
		Triple tre = new TripleJena();
		tre.initialise(new URI(blanc_));

		cal.setTime(now); //or date of archive stop archiving
		tre.relate(pre, cal);

		ar.addTriple(tre);
	}

	if (count > 0) {
		int m_index = linkbf.lastIndexOf("\"memento\"");
		linkbf.insert(m_index + 1, "last ");
	}

	ORESerialiser serial = null;
	if (format.equals("rdf")) {
		serial = ORESerialiserFactory.getInstance("RDF/XML");
		response.setContentType("application/rdf+xml");
	}
	//else if (format.equals("atom")) {
	//	serial = ORESerialiserFactory.getInstance("ATOM-1.0");
	//}
	//else if (format.equals ("html")) {
	//	serial = ORESerialiserFactory.getInstance("RDFa");
	//}
	//removed n3 because serialization of the date to the String type
	//else if (format.equals("n3")) {
	//serial = ORESerialiserFactory.getInstance("N3");

	//response.setContentType("text/n3");
	//}
	else if (format.equals("link")) {
		PrintWriter pw = response.getWriter();

		response.setContentType("application/link-format");
		pw.print(linkbf.toString());
		pw.flush();

	} else {
		// TODO: this should be handled in TimeBundleParser to allow
		//       usual Exception rendering to happen.
		response.sendError(404, "Unknown TimeMap serialization");
	}
	if (serial != null) {
		ResourceMapDocument doc = serial.serialise(rem);
		// TODO: this could get really big. Any way to stream the data out
		//       so we don't need another copy beyond the ResourceMap, 
		//       and other helper objects?
		String serialisation = doc.toString();
		if (format.equals("rdf")) {
			//bug in jena? did not serialise date to date type but to string type // stupid fix will need investigate it 
			serialisation = serialisation
					.replaceAll(
							"end rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string",
							"end rdf:datatype=\"http://www.w3.org/2001/XMLSchema#dateTime");
			serialisation = serialisation
					.replaceAll(
							"start rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string",
							"start rdf:datatype=\"http://www.w3.org/2001/XMLSchema#dateTime");
		}
		PrintWriter pw = response.getWriter();
		pw.print(serialisation);
		pw.flush();
	}

    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}