package edu.asu.ows.wfs;

public abstract class GMLAbstract {
	protected String namespace = "localhost";
	protected String namespaceUrl = "http://localhost";

	public void setNamespace(String namespace_prefix, String namespace_url) {
		this.namespace = namespace_prefix;
		this.namespaceUrl = namespace_url;
	}
}
