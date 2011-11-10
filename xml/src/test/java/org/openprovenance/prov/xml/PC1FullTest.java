package org.openprovenance.prov.xml;
import java.io.File;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.JAXBException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Unit test for simple Provenance Challenge 1 like workflow.
 */
public class PC1FullTest 
    extends TestCase
{
    public static ProvFactory pFactory=new ProvFactory();

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public PC1FullTest( String testName )
    {
        super( testName );
    }

    public boolean urlFlag=true;

    /**
     * @return the suite of tests being tested
     */


    static Container graph1;


    public void testPC1Full() throws JAXBException
    {
        Container graph=makePC1FullGraph(pFactory);

        ProvSerialiser serial=ProvSerialiser.getThreadProvSerialiser();
        serial.serialiseContainer(new File("target/pc1-full.xml"),graph,true);

        
        //System.out.println(sw);

        graph1=graph;
        System.out.println("PC1Full Test asserting True");
        assertTrue( true );


    }

    static String PATH_PROPERTY="http://openprovenance.org/primitives#path";
    static String URL_PROPERTY="http://openprovenance.org/primitives#url";
    static String PRIMITIVE_PROPERTY="http://openprovenance.org/primitives#primitive";
    static String FILE_LOCATION="/shomewhere/pc1/";
    static String URL_LOCATION="http://www.ipaw.info/challenge/";

    static String PRIMITIVE_ALIGN_WARP="http://openprovenance.org/primitives#align_warp";
    static String PRIMITIVE_RESLICE="http://openprovenance.org/primitives#reslice";
    static String PRIMITIVE_SOFTMEAN="http://openprovenance.org/primitives#softmean";
    static String PRIMITIVE_CONVERT="http://openprovenance.org/primitives#convert";
    static String PRIMITIVE_SLICER="http://openprovenance.org/primitives#slicer";


    public Entity newFile(ProvFactory pFactory,
                          String id,
                          String label,
                          String file,
                          String location) {
                            
        Entity a=pFactory.newEntity(id,
                                    label);
        pFactory.addType(a,"http://openprovenance.org/primitives#File");

        addUrl(a,location+file);


        return a;
    }


    public Entity newParameter(ProvFactory pFactory,
                                 String id,
                                 String label,
                                 String value) {
                            
        Entity a=pFactory.newEntity(id,
                                    label);
        pFactory.addType(a,"http://openprovenance.org/primitives#String");

        addValue(a,value);




        return a;
    }



    public Container makePC1FullGraph(ProvFactory pFactory) {
        if (urlFlag) {
            return makePC1FullGraph(pFactory,URL_LOCATION,URL_LOCATION);
        } else {
            return makePC1FullGraph(pFactory,FILE_LOCATION,"./");
        }
    }

    public void addValue(HasAttributes p1, String val) {
        pFactory.addAttribute(p1,
                              "http://www.ipaw.info/pc1/",
                              "pc1",
                              "value",
                              val);
    }

    public void addUrl(HasAttributes p1, String val) {
        pFactory.addAttribute(p1,
                              "http://www.ipaw.info/pc1/",
                              "pc1",
                              "url",
                              val);
    }


    public Container makePC1FullGraph(ProvFactory pFactory, String inputLocation, String outputLocation)
    {

        //        Collection<Account> black=Collections.singleton(pFactory.newAccount("black"));
        Collection<Account> black=new LinkedList();
        

        Activity p0=pFactory.newActivity("p0",
                                       "PC1Full Workflow");

        Activity p1=pFactory.newActivity("p1",
                                       "align_warp 1");
        
        pFactory.addType(p1,PRIMITIVE_ALIGN_WARP);

        Activity p2=pFactory.newActivity("p2",
                                       "align_warp 2");
        pFactory.addType(p2,PRIMITIVE_ALIGN_WARP);


        Activity p3=pFactory.newActivity("p3",
                                       "align_warp 3");
        pFactory.addType(p3,PRIMITIVE_ALIGN_WARP);

        Activity p4=pFactory.newActivity("p4",
                                       "align_warp 4");

        pFactory.addType(p4,PRIMITIVE_ALIGN_WARP);


        Activity p5=pFactory.newActivity("p5",
                                       "Reslice 1");
        pFactory.addType(p5,PRIMITIVE_RESLICE);

        Activity p6=pFactory.newActivity("p6",
                                       "Reslice 2");
        pFactory.addType(p6,PRIMITIVE_RESLICE);

        Activity p7=pFactory.newActivity("p7",
                                       "Reslice 3");
        pFactory.addType(p7,PRIMITIVE_RESLICE);

        Activity p8=pFactory.newActivity("p8",
                                       "Reslice 4");
        pFactory.addType(p8,PRIMITIVE_RESLICE);

        Activity p9=pFactory.newActivity("p9",
                                       "Softmean");
        pFactory.addType(p9,PRIMITIVE_SOFTMEAN);

        Activity p10=pFactory.newActivity("p10",
                                        "Slicer 1");

        pFactory.addType(p10,PRIMITIVE_SLICER);

        Activity p11=pFactory.newActivity("p11",
                                        "Slicer 2");
        pFactory.addType(p11,PRIMITIVE_SLICER);

        Activity p12=pFactory.newActivity("p12",
                                        "Slicer 3");
        pFactory.addType(p12,PRIMITIVE_SLICER);

        Activity p13=pFactory.newActivity("p13",
                                        "Convert 1");

        pFactory.addType(p13,PRIMITIVE_CONVERT);

        Activity p14=pFactory.newActivity("p14",
                                        "Convert 2");

        pFactory.addType(p14,PRIMITIVE_CONVERT);

        Activity p15=pFactory.newActivity("p15",
                                        "Convert 3");

        pFactory.addType(p15,PRIMITIVE_CONVERT);


        Agent ag1=pFactory.newAgent("ag1",
                                    "John Doe");


        Entity a1=newFile(pFactory,
                             "a1",
                             "Reference Image",
                             "reference.img",
                             inputLocation);

        Entity a2=newFile(pFactory,
                             "a2",
                             "Reference Header",
                             "reference.hdr",
                             inputLocation);

        Entity a3=newFile(pFactory,
                             "a3",
                             "Anatomy I1",
                             "anatomy1.img",
                             inputLocation);

        Entity a4=newFile(pFactory,
                             "a4",
                             "Anatomy H1",
                             "anatomy1.hdr",
                             inputLocation);

        Entity a5=newFile(pFactory,
                             "a5",
                             "Anatomy I2",
                             "anatomy2.img",
                             inputLocation);

        Entity a6=newFile(pFactory,
                             "a6",
                             "Anatomy H2",
                             "anatomy2.hdr",
                             inputLocation);

        Entity a7=newFile(pFactory,
                             "a7",
                             "Anatomy I3",
                             "anatomy3.img",
                             inputLocation);

        Entity a8=newFile(pFactory,
                             "a8",
                             "Anatomy H3",
                             "anatomy3.hdr",
                             inputLocation);

        Entity a9=newFile(pFactory,
                             "a9",
                             "Anatomy I4",
                             "anatomy4.img",
                             inputLocation);

        Entity a10=newFile(pFactory,
                              "a10",
                              "Anatomy H4",
                              "anatomy4.hdr",
                              inputLocation);

        Entity a11=newFile(pFactory,
                             "a11",
                             "Warp Params1",
                             "warp1.warp",
                             outputLocation);

        Entity a12=newFile(pFactory,
                             "a12",
                             "Warp Params2",
                             "warp2.warp",
                             outputLocation);

        Entity a13=newFile(pFactory,
                             "a13",
                             "Warp Params3",
                             "warp3.warp",
                             outputLocation);

        Entity a14=newFile(pFactory,
                             "a14",
                             "Warp Params4",
                             "warp4.warp",
                             outputLocation);

        Entity a15=newFile(pFactory,
                             "a15",
                             "Resliced I1",
                             "resliced1.img",
                             outputLocation);

        Entity a16=newFile(pFactory,
                             "a16",
                             "Resliced H1",
                             "resliced1.hdr",
                             outputLocation);
        Entity a17=newFile(pFactory,
                             "a17",
                             "Resliced I2",
                             "resliced2.img",
                             outputLocation);
        Entity a18=newFile(pFactory,
                             "a18",
                             "Resliced H2",
                             "resliced2.hdr",
                             outputLocation);
        Entity a19=newFile(pFactory,
                             "a19",
                             "Resliced I3",
                             "resliced3.img",
                             outputLocation);
        Entity a20=newFile(pFactory,
                             "a20",
                             "Resliced H3",
                             "resliced3.hdr",
                             outputLocation);
        Entity a21=newFile(pFactory,
                             "a21",
                             "Resliced I4",
                             "resliced4.img",
                             outputLocation);
        Entity a22=newFile(pFactory,
                             "a22",
                             "Resliced H4",
                             "resliced4.hdr",
                             outputLocation);


        Entity a23=newFile(pFactory,
                             "a23",
                             "Atlas Image",
                             "atlas.img",
                             outputLocation);
        Entity a24=newFile(pFactory,
                             "a24",
                             "Atlas Header",
                             "atlas.hdr",
                             outputLocation);


        Entity a25=newFile(pFactory,
                             "a25",
                             "Atlas X Slice",
                             "atlas-x.pgm",
                             outputLocation);
        Entity a25p=newParameter(pFactory,
                                   "a25p",
                                   "slicer param 1",
                                   "-x .5");

        Entity a26=newFile(pFactory,
                             "a26",
                             "Atlas Y Slice",
                             "atlas-y.pgm",
                             outputLocation);
        Entity a26p=newParameter(pFactory,
                                   "a26p",
                                   "slicer param 2",
                                   "-y .5");
        Entity a27=newFile(pFactory,
                             "a27",
                             "Atlas Z Slice",
                             "atlas-z.pgm",
                             outputLocation);
        Entity a27p=newParameter(pFactory,
                                   "a27p",
                                   "slicer param 3",
                                   "-z .5");
        Entity a28=newFile(pFactory,
                             "a28",
                             "Atlas X Graphic",
                             "atlas-x.gif",
                             outputLocation);
        Entity a29=newFile(pFactory,
                             "a29",
                             "Atlas Y Graphic",
                             "atlas-y.gif",
                             outputLocation);
        Entity a30=newFile(pFactory,
                             "a30",
                             "Atlas Z Graphic",
                             "atlas-z.gif",
                             outputLocation);

        Used u1=pFactory.newUsed(p1,"img",a3);
        Used u2=pFactory.newUsed(p1,"hdr",a4);
        Used u3=pFactory.newUsed(p1,"imgRef",a1);
        Used u4=pFactory.newUsed(p1,"hdrRef",a2);
        Used u5=pFactory.newUsed(p2,"img",a5);
        Used u6=pFactory.newUsed(p2,"hdr",a6);
        Used u7=pFactory.newUsed(p2,"imgRef",a1);
        Used u8=pFactory.newUsed(p2,"hdrRef",a2);
        Used u9=pFactory.newUsed(p3,"img",a7);
        Used u10=pFactory.newUsed(p3,"hdr",a8);
        Used u11=pFactory.newUsed(p3,"imgRef",a1);
        Used u12=pFactory.newUsed(p3,"hdrRef",a2);
        Used u13=pFactory.newUsed(p4,"img",a9);
        Used u14=pFactory.newUsed(p4,"hdr",a10);
        Used u15=pFactory.newUsed(p4,"imgRef",a1);
        Used u16=pFactory.newUsed(p4,"hdrRef",a2);

        Used u17=pFactory.newUsed(p5,"in",a11);
        Used u18=pFactory.newUsed(p6,"in",a12);
        Used u19=pFactory.newUsed(p7,"in",a13);
        Used u20=pFactory.newUsed(p8,"in",a14);

        Used u21=pFactory.newUsed(p9,"i1",a15);
        Used u22=pFactory.newUsed(p9,"h1",a16);
        Used u23=pFactory.newUsed(p9,"i2",a17);
        Used u24=pFactory.newUsed(p9,"h2",a18);
        Used u25=pFactory.newUsed(p9,"i3",a19);
        Used u26=pFactory.newUsed(p9,"h3",a20);
        Used u27=pFactory.newUsed(p9,"i4",a21);
        Used u28=pFactory.newUsed(p9,"h4",a22);

        Used u29=pFactory.newUsed(p10,"img",a23);
        Used u30=pFactory.newUsed(p10,"hdr",a24);
        Used u30p=pFactory.newUsed(p10,"param",a25p);
        Used u31=pFactory.newUsed(p11,"img",a23);
        Used u32=pFactory.newUsed(p11,"hdr",a24);
        Used u32p=pFactory.newUsed(p11,"param",a26p);
        Used u33=pFactory.newUsed(p12,"img",a23);
        Used u34=pFactory.newUsed(p12,"hdr",a24);
        Used u34p=pFactory.newUsed(p12,"param",a27p);

        Used u35=pFactory.newUsed(p13,"in",a25);
        Used u36=pFactory.newUsed(p14,"in",a26);
        Used u37=pFactory.newUsed(p15,"in",a27);



        WasGeneratedBy wg1=pFactory.newWasGeneratedBy(a11,"out",p1);
        WasGeneratedBy wg2=pFactory.newWasGeneratedBy(a12,"out",p2);
        WasGeneratedBy wg3=pFactory.newWasGeneratedBy(a13,"out",p3);
        WasGeneratedBy wg4=pFactory.newWasGeneratedBy(a14,"out",p4);

        WasGeneratedBy wg5=pFactory.newWasGeneratedBy(a15,"img",p5);
        WasGeneratedBy wg6=pFactory.newWasGeneratedBy(a16,"hdr",p5);
        WasGeneratedBy wg7=pFactory.newWasGeneratedBy(a17,"img",p6);
        WasGeneratedBy wg8=pFactory.newWasGeneratedBy(a18,"hdr",p6);
        WasGeneratedBy wg9=pFactory.newWasGeneratedBy(a19,"img",p7);
        WasGeneratedBy wg10=pFactory.newWasGeneratedBy(a20,"hdr",p7);
        WasGeneratedBy wg11=pFactory.newWasGeneratedBy(a21,"img",p8);
        WasGeneratedBy wg12=pFactory.newWasGeneratedBy(a22,"hdr",p8);

        WasGeneratedBy wg13=pFactory.newWasGeneratedBy(a23,"img",p9);
        WasGeneratedBy wg14=pFactory.newWasGeneratedBy(a24,"hdr",p9);

        WasGeneratedBy wg15=pFactory.newWasGeneratedBy(a25,"out",p10);
        WasGeneratedBy wg16=pFactory.newWasGeneratedBy(a26,"out",p11);
        WasGeneratedBy wg17=pFactory.newWasGeneratedBy(a27,"out",p12);

        WasGeneratedBy wg18=pFactory.newWasGeneratedBy(a28,"out",p13);
        WasGeneratedBy wg19=pFactory.newWasGeneratedBy(a29,"out",p14);
        WasGeneratedBy wg20=pFactory.newWasGeneratedBy(a30,"out",p15);


        WasDerivedFrom wd1=pFactory.newWasDerivedFrom(a11,a1);
        WasDerivedFrom wd2=pFactory.newWasDerivedFrom(a11,a2);
        WasDerivedFrom wd3=pFactory.newWasDerivedFrom(a11,a3);
        WasDerivedFrom wd4=pFactory.newWasDerivedFrom(a11,a4);
        WasDerivedFrom wd5=pFactory.newWasDerivedFrom(a12,a1);
        WasDerivedFrom wd6=pFactory.newWasDerivedFrom(a12,a2);
        WasDerivedFrom wd7=pFactory.newWasDerivedFrom(a12,a5);
        WasDerivedFrom wd8=pFactory.newWasDerivedFrom(a12,a6);
        WasDerivedFrom wd9=pFactory.newWasDerivedFrom(a13,a1);
        WasDerivedFrom wd10=pFactory.newWasDerivedFrom(a13,a2);
        WasDerivedFrom wd11=pFactory.newWasDerivedFrom(a13,a7);
        WasDerivedFrom wd12=pFactory.newWasDerivedFrom(a13,a8);
        WasDerivedFrom wd13=pFactory.newWasDerivedFrom(a14,a1);
        WasDerivedFrom wd14=pFactory.newWasDerivedFrom(a14,a2);
        WasDerivedFrom wd15=pFactory.newWasDerivedFrom(a14,a9);
        WasDerivedFrom wd16=pFactory.newWasDerivedFrom(a14,a10);


        WasDerivedFrom wd17=pFactory.newWasDerivedFrom(a15,a11);
        WasDerivedFrom wd18=pFactory.newWasDerivedFrom(a16,a11);
        WasDerivedFrom wd19=pFactory.newWasDerivedFrom(a17,a12);
        WasDerivedFrom wd20=pFactory.newWasDerivedFrom(a18,a12);
        WasDerivedFrom wd21=pFactory.newWasDerivedFrom(a19,a13);
        WasDerivedFrom wd22=pFactory.newWasDerivedFrom(a20,a13);
        WasDerivedFrom wd23=pFactory.newWasDerivedFrom(a21,a14);
        WasDerivedFrom wd24=pFactory.newWasDerivedFrom(a22,a14);


        WasDerivedFrom wd25=pFactory.newWasDerivedFrom(a23,a15);
        WasDerivedFrom wd26=pFactory.newWasDerivedFrom(a23,a16);
        WasDerivedFrom wd27=pFactory.newWasDerivedFrom(a23,a17);
        WasDerivedFrom wd28=pFactory.newWasDerivedFrom(a23,a18);
        WasDerivedFrom wd29=pFactory.newWasDerivedFrom(a23,a19);
        WasDerivedFrom wd30=pFactory.newWasDerivedFrom(a23,a20);
        WasDerivedFrom wd31=pFactory.newWasDerivedFrom(a23,a21);
        WasDerivedFrom wd32=pFactory.newWasDerivedFrom(a23,a22);

        WasDerivedFrom wd33=pFactory.newWasDerivedFrom(a24,a15);
        WasDerivedFrom wd34=pFactory.newWasDerivedFrom(a24,a16);
        WasDerivedFrom wd35=pFactory.newWasDerivedFrom(a24,a17);
        WasDerivedFrom wd36=pFactory.newWasDerivedFrom(a24,a18);
        WasDerivedFrom wd37=pFactory.newWasDerivedFrom(a24,a19);
        WasDerivedFrom wd38=pFactory.newWasDerivedFrom(a24,a20);
        WasDerivedFrom wd39=pFactory.newWasDerivedFrom(a24,a21);
        WasDerivedFrom wd40=pFactory.newWasDerivedFrom(a24,a22);

        WasDerivedFrom wd41=pFactory.newWasDerivedFrom(a25,a23);
        WasDerivedFrom wd42=pFactory.newWasDerivedFrom(a25,a24);
        WasDerivedFrom wd43=pFactory.newWasDerivedFrom(a26,a23);
        WasDerivedFrom wd44=pFactory.newWasDerivedFrom(a26,a24);
        WasDerivedFrom wd45=pFactory.newWasDerivedFrom(a27,a23);
        WasDerivedFrom wd46=pFactory.newWasDerivedFrom(a27,a24);

        WasDerivedFrom wd47=pFactory.newWasDerivedFrom(a28,a25);
        WasDerivedFrom wd48=pFactory.newWasDerivedFrom(a29,a26);
        WasDerivedFrom wd49=pFactory.newWasDerivedFrom(a30,a27);



        WasControlledBy wc1=pFactory.newWasControlledBy(p1,"user",ag1);



        Container graph=pFactory.newContainer(black,
                                            new Activity[] {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15},
                                            new Entity[] {a1,a2,a5,a6,a3,a4,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17,a18,a19,a20,
                                                            a21,a22,a23,a24,a25,a25p,a26,a26p,a27,a27p,a28,a29,a30},
                                            new Agent[] { //ag1
                                                        },
                                            new Object[] {u1,u2,u3,u4,u5,u6,u7,u8,u9,u10,u11,u12,u13,u14,u15,u16,u17,u18,u19,u20,
                                                          u21,u22,u23,u24,u25,u26,u27,u28,u29,u30,u31,u32,u33,u34,u30p,u32p,u34p,u35,u36,u37,
                                                          wg1,wg2,wg3,wg4,wg5,wg6,wg7,wg8,wg9,wg10,wg11,wg12,wg13,wg14,
                                                          wg15,wg16,wg17,wg18,wg19,wg20,
                                                          wd1,wd2,wd3,wd4,wd5,wd6,wd7,wd8,wd9,wd10,wd11,wd12,wd13,wd14,wd15,wd16,
                                                          wd17,wd18,wd19,wd20,wd21,wd22,wd23,wd24,wd25,wd26,wd27,wd28,wd29,wd30,wd31,
                                                          wd32,wd33,wd34,wd35,wd36,wd37,wd38,wd39,wd40,wd41,wd42,wd43,wd44,wd45,wd46,
                                                          wd47,wd48,wd49
                                                          //wc1,
                                            } );



        return graph;
    }
    




    public void testCopyPC1Full() throws java.io.FileNotFoundException,  java.io.IOException   {
        ProvFactory pFactory=new ProvFactory();

        Container graph2=pFactory.newContainer(graph1);

        assertTrue( "self graph1 differ", graph1.equals(graph1) );        

        assertTrue( "self graph2 differ", graph2.equals(graph2) );        

        assertTrue( "graph1 graph2 differ", graph1.equals(graph2) );        
        
    }

}
