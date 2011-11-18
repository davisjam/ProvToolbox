package org.openprovenance.prov.xml;
import java.util.Collection;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;
import javax.xml.bind.JAXBElement;
import java.util.GregorianCalendar;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.DatatypeConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


/** A stateless factory for PROV objects. */

public class ProvFactory implements CommonURIs {

    public static String roleIdPrefix="r_";
    public static String usedIdPrefix="u_";
    public static String wasGenerateByIdPrefix="g_";
    public static String wasDerivedFromIdPrefix="d_";
    public static String wasTriggeredByIdPrefix="t_";
    public static String wasControlledByIdPrefix="c_";
    public static String containerIdPrefix="gr_";

    public static final String packageList=
        "org.openprovenance.prov.xml";

    public String getPackageList() {
        return packageList;
    }

    private final static ProvFactory oFactory=new ProvFactory();

    public static ProvFactory getFactory() {
        return oFactory;
    }

    protected ObjectFactory of;

    protected DatatypeFactory dataFactory;

    void init() {
        try {
            dataFactory= DatatypeFactory.newInstance ();
        } catch (DatatypeConfigurationException ex) {
            throw new RuntimeException (ex);
        }
    }



    public ProvFactory() {
        of=new ObjectFactory();
        init();
    }

    public ProvFactory(ObjectFactory of) {
        this.of=of;
        init();
    }

    public ObjectFactory getObjectFactory() {
        return of;
    }

    public ActivityRef newActivityRef(Activity p) {
        ActivityRef res=of.createActivityRef();
        res.setRef(p.getId());
        return res;
    }

    public ActivityRef newActivityRef(String id) {
        ActivityRef res=of.createActivityRef();
        res.setRef(id);
        return res;
    }

    public AnnotationRef newAnnotationRef(Annotation a) {
        AnnotationRef res=of.createAnnotationRef();
        res.setRef(a);
        return res;
    }

    public EntityRef newEntityRef(Entity a) {
        EntityRef res=of.createEntityRef();
        res.setRef(a.getId());
        return res;
    }

    public EntityRef newEntityRef(String id) {
        EntityRef res=of.createEntityRef();
        res.setRef(id);
        return res;
    }

    public AgentRef newAgentRef(Agent a) {
        AgentRef res=of.createAgentRef();
        res.setRef(a.getId());
        return res;
    }

    public AgentRef newAgentRef(String id) {
        AgentRef res=of.createAgentRef();
        res.setRef(id);
        return res;
    }




    public DependencyRef newDependencyRef(WasGeneratedBy edge) {
        DependencyRef res=of.createDependencyRef();
        res.setRef(edge);
        return res;
    }

    public DependencyRef newDependencyRef(Used edge) {
        DependencyRef res=of.createDependencyRef();
        res.setRef(edge);
        return res;
    }
    public DependencyRef newDependencyRef(WasDerivedFrom edge) {
        DependencyRef res=of.createDependencyRef();
        res.setRef(edge);
        return res;
    }

    public DependencyRef newDependencyRef(WasControlledBy edge) {
        DependencyRef res=of.createDependencyRef();
        res.setRef(edge);
        return res;
    }
    public DependencyRef newDependencyRef(WasInformedBy edge) {
        DependencyRef res=of.createDependencyRef();
        res.setRef(edge);
        return res;
    }



    public Activity newActivity(String pr) {
        return newActivity(pr,null);
    }

    public Activity newActivity(String pr,
                                String label) {
        Activity res=of.createActivity();
        res.setId(pr);
        if (label!=null) addLabel(res,label);
        return res;
    }

    public Agent newAgent(String ag) {
        return newAgent(ag,null);
    }

    public Agent newAgent(String ag,
                          String label) {
        Agent res=of.createAgent();
        res.setId(ag);
        if (label!=null) addLabel(res,label);
        return res;
    }

    public Account newAccount(String id) {
        Account res=of.createAccount();
        res.setId(id);
        return res;
    }
    public Account newAccount(String id, String label) {
        Account res=of.createAccount();
        res.setId(id);
        if (label!=null) addAnnotation(res,newLabel(label));
        return res;
    }

    public EmbeddedAnnotation newLabel(String label) {
        EmbeddedAnnotation res=of.createEmbeddedAnnotation();
        res.getProperty().add(newProperty(LABEL_PROPERTY,label));
        return res;
    }

    public EmbeddedAnnotation newValue(Object value, String encoding) {
        EmbeddedAnnotation res=of.createEmbeddedAnnotation();
        res.getProperty().add(newProperty(VALUE_PROPERTY,value));
        res.getProperty().add(newProperty(ENCODING_PROPERTY,encoding));
        return res;
    }



    
    public String getLabel(EmbeddedAnnotation annotation) {
        if (annotation==null) return null;
        for (Property prop: annotation.getProperty()) {
            if (prop.getAttribute().equals(LABEL_PROPERTY)) {
                return (String) prop.getValue();
            }
        }
        return null;
    }


    public String getType(EmbeddedAnnotation annotation) {
        if (annotation==null) return null;
        for (Property prop: annotation.getProperty()) {
            if (prop.getAttribute().equals(TYPE_PROPERTY)) {
                return (String) prop.getValue();
            }
        }
        return null;
    }

    public Object getValue(EmbeddedAnnotation annotation) {
        if (annotation==null) return null;
        for (Property prop: annotation.getProperty()) {
            if (prop.getAttribute().equals(VALUE_PROPERTY)) {
                return prop.getValue();
            }
        }
        return null;
    }

    public String getEncoding(EmbeddedAnnotation annotation) {
        if (annotation==null) return null;
        for (Property prop: annotation.getProperty()) {
            if (prop.getAttribute().equals(ENCODING_PROPERTY)) {
                return (String) prop.getValue();
            }
        }
        return null;
    }



    /** Return the value of the value property in the first annotation. */

    public Object getValue(List<EmbeddedAnnotation> annotations) {
        for (EmbeddedAnnotation ann: annotations) {
            Object value=getValue(ann);
            if (value!=null) return value;
        }
        return null;
    }



    /** Return the value of the label property in the first annotation. */
    public String getLabel(List<EmbeddedAnnotation> annotations) {
        for (EmbeddedAnnotation ann: annotations) {
            String label=getLabel(ann);
            if (label!=null) return label;
        }
        return null;
    }


    /** Return the value of the type property in the first annotation. */

    public String getType(List<EmbeddedAnnotation> annotations) {
        for (EmbeddedAnnotation ann: annotations) {
            String type=getType(ann);
            if (type!=null) return type;
        }
        return null;
    }



    /** Return the value of the value property. */

    public List<Object> getValues(List<EmbeddedAnnotation> annotations) {
        List<Object> res=new LinkedList();
        for (EmbeddedAnnotation ann: annotations) {
            Object value=getValue(ann);
            if (value!=null) res.add(value);
        }
        return res;
    }



    /** Return the value of the label property. */
    public List<String> getLabels(List<EmbeddedAnnotation> annotations) {
        List<String> res=new LinkedList();
        for (EmbeddedAnnotation ann: annotations) {
            String label=getLabel(ann);
            if (label!=null) res.add(label);
        }
        return res;
    }


    /** Return the value of the type property. */

    public List<String> getTypes(List<EmbeddedAnnotation> annotations) {
        List<String> res=new LinkedList();
        for (EmbeddedAnnotation ann: annotations) {
            String type=getType(ann);
            if (type!=null) res.add(type);
        }
        return res;
    }




    /** Generic accessor for annotable entities. */
    public String getLabel(Annotable annotable) {
        return getLabel(annotable.getAnnotation());
    }

    /** Generic accessor for annotable entities. */
    public String getType(Annotable annotable) {
        return getType(annotable.getAnnotation());
    }


    public EmbeddedAnnotation newType(String type) {
        EmbeddedAnnotation res=of.createEmbeddedAnnotation();
        res.getProperty().add(newProperty(TYPE_PROPERTY,type));
        return res;
    }

    // public void addValue(Entity annotable, Object value, String encoding) {
    //     addAnnotation(annotable,newValue(value,encoding));
    // }

    public void addAnnotation(Annotable annotable, EmbeddedAnnotation ann) {
        //annotable.getAnnotation().add(ann);
        //	annotable.getAnnotation().add(ann);
        EmbeddedAnnotation ea=annotable.getAnnotation();
        if (ea==null) {
            annotable.setAnnotation(ann);
        } else {
            ea.getProperty().addAll(ann.getProperty());
        }
    }



    public XMLGregorianCalendar
        newXMLGregorianCalendar(GregorianCalendar gc) {
        return dataFactory.newXMLGregorianCalendar(gc);
    }



	public XMLGregorianCalendar newTime (Date date) {
        GregorianCalendar gc=new GregorianCalendar();
        gc.setTime(date);
        return newXMLGregorianCalendar(gc);
    }

	public XMLGregorianCalendar newTimeNow () {
        return newTime(new Date());
    }


    public void addAnnotation(Annotable annotable, List<EmbeddedAnnotation> anns) {
        for (EmbeddedAnnotation ann: anns) {        
            addAnnotation(annotable,ann);
        }
    }







    /** By default, no auto generation of Id.  Override this behaviour if required. */
    public String autoGenerateId(String prefix) {
        return null;
    }

    /** Conditional autogeneration of Id. By default, no auto
     * generation of Id.  Override this behaviour if required. */
    public String autoGenerateId(String prefix, String id) {
        return id;
    }


    public Entity newEntity(Entity a) {
        Entity res=newEntity(a.getId());
        return res;
    }
    public Activity newActivity(Activity a) {
        Activity res=newActivity(a.getId());
        return res;
    }
    public Agent newAgent(Agent a) {
        Agent res=newAgent(a.getId());
        return res;
    }

    public Account newAccount(Account acc) {
        Account res=newAccount(acc.getId());
        addNewAnnotations(res,acc.getAnnotation());
        return res;
    }

    public void addNewAnnotations(Annotable res,
                                  org.openprovenance.prov.xml.EmbeddedAnnotation ea) {
        if (ea.getId()!=null) 
            addAnnotation(res,newEmbeddedAnnotation(ea.getId(),
                                                    ea.getProperty(),
                                                    null));
    }


    public Entity newEntity(String id) {
        return newEntity(id,null);
    }

    public Entity newEntity(String id,
                            String label) {
        Entity res=of.createEntity();
        res.setId(id);
        if (label!=null) addLabel(res,label);
        return res;
    }



    public Used newUsed(String id,
                        ActivityRef pid,
                        String role,
                        EntityRef aid) {
        Used res=of.createUsed();
        res.setId(autoGenerateId(usedIdPrefix,id));
        res.setEffect(pid);
        addRole(res,role);
        res.setCause(aid);
        return res;
    }


    public void addRole(HasExtensibility a,                                  
                        String role) {
        if (role!=null) {
            // addAttribute(a,
            //              "http://openprovenance.org/prov/xml#",
            //              "prov",
            //              "role",
            //              role);
            TypedLiteral tl=newTypedLiteral(role,"xs:string");
            JAXBElement<TypedLiteral> je=of.createRole(tl);
            addAttribute(a,je);
        }
    }
    
    public TypedLiteral newTypedLiteral(String value,
                                        String type) {
        TypedLiteral res=of.createTypedLiteral();
        res.setValue(value);
        res.setType(type);
        return res;
    }

    public Used newUsed(Activity p,
                        String role,
                        Entity a) {
        Used res=newUsed(null,p,role,a);
        return res;
    }

    public Used newUsed(String id,
                        Activity p,
                        String role,
                        Entity a) {
        ActivityRef pid=newActivityRef(p);
        EntityRef aid=newEntityRef(a);
        return newUsed(id,pid,role,aid);
    }


    public Used newUsed(String id,
                        Activity p,
                        String role,
                        Entity a,
                        String type) {
        Used res=newUsed(id,p,role,a);
        addType(res,type);
        return res;
    }

    public Used newUsed(Used u) {
        Used u1=newUsed(u.getId(),
                        u.getEffect(),
                        null,
                        u.getCause());
        u1.getAny().addAll(u.getAny());
        return u1;
    }

    public WasControlledBy newWasControlledBy(WasControlledBy c) {
        WasControlledBy wcb=newWasControlledBy(c.getEffect(),
                                               null,
                                               c.getCause());
        wcb.setId(c.getId());
        wcb.getAny().addAll(c.getAny());
        return wcb;
    }

    public WasGeneratedBy newWasGeneratedBy(WasGeneratedBy g) {
        WasGeneratedBy wgb=newWasGeneratedBy(g.getId(),
                                             g.getEffect(),
                                             null,
                                             g.getCause());
        wgb.setId(g.getId());
        wgb.getAny().addAll(g.getAny());
        return wgb;
    }

    public WasDerivedFrom newWasDerivedFrom(WasDerivedFrom d) {
        WasDerivedFrom wdf=newWasDerivedFrom(d.getId(),
                                             d.getEffect(),
                                             d.getCause());
        wdf.getAny().addAll(d.getAny());
        return wdf;
    }

    public WasInformedBy newWasInformedBy(WasInformedBy d) {
        WasInformedBy wtb=newWasInformedBy(d.getId(),
                                           d.getEffect(),
                                           d.getCause());
        wtb.setId(d.getId());
        wtb.getAny().addAll(d.getAny());
        return wtb;
    }



    public WasGeneratedBy newWasGeneratedBy(String id,
                                            EntityRef aid,
                                            String role,
                                            ActivityRef pid) {
        WasGeneratedBy res=of.createWasGeneratedBy();
        res.setId(autoGenerateId(wasGenerateByIdPrefix,id));
        res.setCause(pid);
        res.setEffect(aid);
        addRole(res,role);
        return res;
    }


    public WasGeneratedBy newWasGeneratedBy(Entity a,
                                            String role,
                                            Activity p) {
        return newWasGeneratedBy(null,a,role,p);
    }

    public WasGeneratedBy newWasGeneratedBy(String id,
                                            Entity a,
                                            String role,
                                            Activity p) {
        EntityRef aid=newEntityRef(a);
        ActivityRef pid=newActivityRef(p);
        LinkedList ll=new LinkedList();
        return  newWasGeneratedBy(id,aid,role,pid);
    }



    public WasGeneratedBy newWasGeneratedBy(String id,
                                            Entity a,
                                            String role,
                                            Activity p,
                                            String type) {
        WasGeneratedBy wgb=newWasGeneratedBy(id,a,role,p);
        addType(wgb,type);
        return wgb;
    }

    public WasControlledBy newWasControlledBy(ActivityRef pid,
                                              String role,
                                              AgentRef agid) {
        return newWasControlledBy(null,pid,role,agid);
    }
    
    public WasControlledBy newWasControlledBy(String id,
                                              ActivityRef pid,
                                              String role,
                                              AgentRef agid) {
        WasControlledBy res=of.createWasControlledBy();
        res.setId(autoGenerateId(wasControlledByIdPrefix,id));
        res.setEffect(pid);
        res.setCause(agid);
        addRole(res,role);
        return res;
    }


    public WasControlledBy newWasControlledBy(Activity p,
                                              String role,
                                              Agent ag) {
        return newWasControlledBy(null,p,role,ag);
    }

    public WasControlledBy newWasControlledBy(String id,
                                              Activity p,
                                              String role,
                                              Agent ag) {
        AgentRef agid=newAgentRef(ag);
        ActivityRef pid=newActivityRef(p);
        return  newWasControlledBy(id,pid,role,agid);
    }

    public WasControlledBy newWasControlledBy(String id,
                                              Activity p,
                                              String role,
                                              Agent ag,
                                              String type) {
        WasControlledBy wcb=newWasControlledBy(id,p,role,ag);
        addType(wcb,type);
        return wcb;
    }


    public WasDerivedFrom newWasDerivedFrom(String id,
                                            EntityRef aid1,
                                            EntityRef aid2) {
        WasDerivedFrom res=of.createWasDerivedFrom();
        res.setId(autoGenerateId(wasDerivedFromIdPrefix,id));
        res.setCause(aid2);
        res.setEffect(aid1);
        return res;
    }


    public WasDerivedFrom newWasDerivedFrom(Entity a1,
                                            Entity a2) {
        return newWasDerivedFrom(null,a1,a2);
    }

    public WasDerivedFrom newWasDerivedFrom(String id,
                                            Entity a1,
                                            Entity a2) {
        EntityRef aid1=newEntityRef(a1);
        EntityRef aid2=newEntityRef(a2);
        return  newWasDerivedFrom(id,aid1,aid2);
    }


    public WasDerivedFrom newWasDerivedFrom(String id,
                                            Entity a1,
                                            Entity a2,
                                            String type) {
        WasDerivedFrom wdf=newWasDerivedFrom(id,a1,a2);
        addType(wdf,type);
        return wdf;
    }



    public WasInformedBy newWasInformedBy(String id,
                                          ActivityRef pid1,
                                          ActivityRef pid2) {
        WasInformedBy res=of.createWasInformedBy();
        res.setId(autoGenerateId(wasTriggeredByIdPrefix,id));
        res.setEffect(pid1);
        res.setCause(pid2);
        return res;
    }

    public WasInformedBy newWasInformedBy(Activity p1,
                                          Activity p2) {
        return newWasInformedBy(null,p1,p2);
    }

    public WasInformedBy newWasInformedBy(String id,
                                          Activity p1,
                                          Activity p2) {
        ActivityRef pid1=newActivityRef(p1);
        ActivityRef pid2=newActivityRef(p2);
        return  newWasInformedBy(id,pid1,pid2);
    }


    public WasInformedBy newWasInformedBy(String id,
                                          Activity p1,
                                          Activity p2,
                                          String type) {
        WasInformedBy wtb=newWasInformedBy(p1,p2);
        wtb.setId(id);
        addType(wtb,type);
        return wtb;
    }



    public void addAttribute(HasExtensibility a, Object o) {
        a.getAny().add(o);
    }


    public void addType(HasExtensibility a,
                        String type) {
        JAXBElement<String> je=of.createType(type);
        addAttribute(a,je);

    }

    public void addLabel(HasExtensibility a,                                  
                         String label) {
        JAXBElement<String> je=of.createLabel(label);
        addAttribute(a,je);
    }


    public void addAttribute(HasExtensibility a,
                             String namespace,
                             String prefix,
                             String localName,                                  
                             String value) {

        a.getAny().add(newAttribute(namespace,
                                        prefix,
                                        localName,
                                        value));
    }

    public Element newAttribute(String namespace,
                                String prefix,
                                String localName,                                  
                                String value) {
        Document doc=oFactory.builder.newDocument();
        Element el=doc.createElementNS(namespace,prefix + ":" + localName);
        el.appendChild(doc.createTextNode(value));
        doc.appendChild(el);
        return el;
    }


    public EmbeddedAnnotation newEmbeddedAnnotation(String id,
                                                    String property,
                                                    Object value) {
        return newEmbeddedAnnotation(id,property,value,null);
    }

    public Annotation newAnnotation(String id,
                                    Entity a,
                                    String property,
                                    Object value) {
        EntityRef aid=newEntityRef(a);
        return newAnnotation(id,aid,property,value);
    }
    public Annotation newAnnotation(String id,
                                    Activity p,
                                    String property,
                                    Object value) {
        ActivityRef pid=newActivityRef(p);
        return newAnnotation(id,pid,property,value);
    }

    public Annotation newAnnotation(String id,
                                    Annotation a,
                                    String property,
                                    Object value) {
        AnnotationRef aid=newAnnotationRef(a);
        return newAnnotation(id,aid,property,value);
    }

    public Annotation newAnnotation(String id,
                                    WasDerivedFrom edge,
                                    String property,
                                    Object value) {
        DependencyRef cid=newDependencyRef(edge);
        return newAnnotation(id,cid,property,value);
    }
    public Annotation newAnnotation(String id,
                                    Used edge,
                                    String property,
                                    Object value) {
        DependencyRef cid=newDependencyRef(edge);
        return newAnnotation(id,cid,property,value);
    }
    public Annotation newAnnotation(String id,
                                    WasGeneratedBy edge,
                                    String property,
                                    Object value) {
        DependencyRef cid=newDependencyRef(edge);
        return newAnnotation(id,cid,property,value);
    }
    public Annotation newAnnotation(String id,
                                    WasControlledBy edge,
                                    String property,
                                    Object value) {
        DependencyRef cid=newDependencyRef(edge);
        return newAnnotation(id,cid,property,value);
    }
    public Annotation newAnnotation(String id,
                                    WasInformedBy edge,
                                    String property,
                                    Object value) {
        DependencyRef cid=newDependencyRef(edge);
        return newAnnotation(id,cid,property,value);
    }


    public Property newProperty(String property,
                                Object value) {
        Property res=of.createProperty();
        res.setAttribute(property);
        res.setValue(value);
        return res;
    }

    public Property newProperty(Property property) {
        return newProperty(property.getAttribute(),property.getValue());
    }


    public void addProperty(Annotation ann, Property p) {
        ann.getProperty().add(p);
    }

    public void addProperty(Annotation ann, List<Property> p) {
        ann.getProperty().addAll(p);
    }

    public void addProperty(EmbeddedAnnotation ann, Property p) {
        ann.getProperty().add(p);
    }

    public void addProperty(EmbeddedAnnotation ann, List<Property> p) {
        ann.getProperty().addAll(p);
    }

    public Annotation newAnnotation(String id,
                                    Ref ref,
                                    String property,
                                    Object value) {
        Annotation res=of.createAnnotation();
        res.setId(id);
        res.setLocalSubject(ref.getRef());
        addProperty(res,newProperty(property,value));
        return res;
    }

    public Annotation newAnnotation(String id,
                                    Object o,
                                    List<Property> properties) {
        Annotation res=of.createAnnotation();
        res.setId(id);
        res.setLocalSubject(o);
        for (Property property: properties) {
            addProperty(res,property);
        }
        return res;
    }

    public Annotation newAnnotation(Annotation ann) {
        Annotation res=newAnnotation(ann.getId(),
                                     ann.getLocalSubject(),
                                     ann.getProperty());
        return res;
    }

    public EmbeddedAnnotation newEmbeddedAnnotation(String id,
                                                    String property,
                                                    Object value,
                                                    Object dummyParameterForAvoidingSameErasure) {
        EmbeddedAnnotation res=of.createEmbeddedAnnotation();
        res.setId(id);
        addProperty(res,newProperty(property,value));
        return res;
    }
    public EmbeddedAnnotation newEmbeddedAnnotation(String id,
                                                    List<Property> properties,
                                                    Object dummyParameterForAvoidingSameErasure) {
        EmbeddedAnnotation res=of.createEmbeddedAnnotation();
        res.setId(id);
        if (properties!=null) {
            addProperty(res,properties);
        }
        return res;
    }


    public Container newContainer(Collection<Account> accs,
                                  Collection<Activity> ps,
                                  Collection<Entity> as,
                                  Collection<Agent> ags,
                                  Collection<Object> lks) {
        return newContainer(null,accs,ps,as,ags,lks,null);
    }

    public Container newContainer(Collection<Account> accs,
                                  Collection<Activity> ps,
                                  Collection<Entity> as,
                                  Collection<Agent> ags,
                                  Collection<Object> lks,
                                  Collection<Annotation> anns) {
        return newContainer(null,accs,ps,as,ags,lks,anns);
    }

    public Container newContainer(String id,
                                  Collection<Account> accs,
                                  Collection<Activity> ps,
                                  Collection<Entity> as,
                                  Collection<Agent> ags,
                                  Collection<Object> lks,
                                  Collection<Annotation> anns)
    {
        Container res=of.createContainer();
        res.setId(autoGenerateId(containerIdPrefix,id));
        if (accs!=null) {
            res.getAccount().addAll(accs);
        }
        if (ps!=null) {
            res.getActivity().addAll(ps);
        }
        if (as!=null) {
            res.getEntity().addAll(as);
        }
        if (ags!=null) {
            res.getAgent().addAll(ags);
        }
        if (lks!=null) {
            Dependencies ccls=of.createDependencies();
            ccls.getUsedOrWasGeneratedByOrWasInformedBy().addAll(lks);
            res.setDependencies(ccls);
        }

        if (anns!=null) {
            Annotations l=of.createAnnotations();
            l.getAnnotation().addAll(anns);
            res.setAnnotations(l);
        }
        return res;
    }

    public Container newContainer(Collection<Account> accs,
                                  Activity [] ps,
                                  Entity [] as,
                                  Agent [] ags,
                                  Object [] lks) 
    {

        return newContainer(accs,
                            ((ps==null) ? null : Arrays.asList(ps)),
                            ((as==null) ? null : Arrays.asList(as)),
                            ((ags==null) ? null : Arrays.asList(ags)),
                            ((lks==null) ? null : Arrays.asList(lks)));
    }
    public Container newContainer(Collection<Account> accs,
                                  Activity [] ps,
                                  Entity [] as,
                                  Agent [] ags,
                                  Object [] lks,
                                  Annotation [] anns) {
        return newContainer(null,accs,ps,as,ags,lks,anns);
    }

    public Container newContainer(String id,
                                  Collection<Account> accs,
                                  Activity [] ps,
                                  Entity [] as,
                                  Agent [] ags,
                                  Object [] lks,
                                  Annotation [] anns) 
    {

        return newContainer(id,
                            accs,
                            ((ps==null) ? null : Arrays.asList(ps)),
                            ((as==null) ? null : Arrays.asList(as)),
                            ((ags==null) ? null : Arrays.asList(ags)),
                            ((lks==null) ? null : Arrays.asList(lks)),
                            ((anns==null) ? null : Arrays.asList(anns)));
    }

    public Container newContainer(Collection<Account> accs,
                                  Collection<Activity> ps,
                                  Collection<Entity> as,
                                  Collection<Agent> ags,
                                  Dependencies lks)
    {
        Container res=of.createContainer();
        //res.setId(autoGenerateId(containerIdPrefix));
        res.getAccount().addAll(accs);
        res.getActivity().addAll(ps);
        res.getEntity().addAll(as);
        res.getAgent().addAll(ags);
        res.setDependencies(lks);
        return res;
    }

    public Container newContainer(Collection<Account> accs,
                                  Collection<Activity> ps,
                                  Collection<Entity> as,
                                  Collection<Agent> ags,
                                  Dependencies lks,
                                  Annotations anns)
    {
        Container res=of.createContainer();
        //res.setId(autoGenerateId(containerIdPrefix));
        res.getAccount().addAll(accs);
        res.getActivity().addAll(ps);
        res.getEntity().addAll(as);
        res.getAgent().addAll(ags);
        res.setDependencies(lks);
        res.setAnnotations(anns);
        return res;
    }



    public Container newContainer(Container graph) {
        return newContainer(graph.getAccount(),
                            graph.getActivity(),
                            graph.getEntity(),
                            graph.getAgent(),
                            graph.getDependencies(),
                            graph.getAnnotations());
    }



    static {
        initBuilder();
    }
    static public DocumentBuilder builder;

	static void initBuilder() {
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			docBuilderFactory.setNamespaceAware(true);
			builder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException ex) {
			throw new RuntimeException(ex);
		}
	}
            
}
