package org.openprovenance.prov.sql;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.jvnet.hyperjaxb3.xml.bind.annotation.adapters.QNameAsString;
import org.jvnet.hyperjaxb3.xml.bind.annotation.adapters.XmlAdapterUtils;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for IDRef complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IDRef">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute ref="{http://www.w3.org/ns/prov#}ref use="required""/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IDRef")
@Entity(name = "IDRef")
@javax.persistence.Cacheable 
@Table(name = "IDREF", uniqueConstraints=@javax.persistence.UniqueConstraint(columnNames={"URI"}))
@Inheritance(strategy = InheritanceType.JOINED)
public class IDRef
    implements Equals, HashCode
{

    @XmlAttribute(name = "ref", namespace = "http://www.w3.org/ns/prov#", required = true)
    protected QName ref;
    
    @XmlAttribute(name = "Hjid")
    protected Long hjid;

    /**
     * Gets the value of the ref property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    @Transient
    public QName getRef() {
        return ref;
    }

    /**
     * Sets the value of the ref property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setRef(QName value) {
        this.ref = value;
    }

    /**
     * Gets the value of the hjid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    @Id
    @Column(name = "HJID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getHjid() {
        return hjid;
    }

    /**
     * Sets the value of the hjid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHjid(Long value) {
        this.hjid = value;
    }

    @Basic
    @Column(name = "URI")
    public String getUri() {
	return ((this.getRef() == null) ? null : this.getRef()
						     .getNamespaceURI()
		+ this.getRef().getLocalPart());
    } 
    
    public void setUri(String uri) {} 
    
    @Basic@Column(name = "REFITEM")
    public String getRefItem() {
        return XmlAdapterUtils.unmarshall(QNameAsString.class, this.getRef());
    }

    public void setRefItem(String target) {
        setRef(XmlAdapterUtils.marshall(QNameAsString.class, target));
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof IDRef)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final IDRef that = ((IDRef) object);
        {
            QName lhsRef;
            lhsRef = this.getRef();
            QName rhsRef;
            rhsRef = that.getRef();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "ref", lhsRef), LocatorUtils.property(thatLocator, "ref", rhsRef), lhsRef, rhsRef)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            QName theRef;
            theRef = this.getRef();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "ref", theRef), currentHashCode, theRef);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

}
