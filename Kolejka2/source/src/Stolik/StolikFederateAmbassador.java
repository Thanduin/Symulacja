package Stolik;

import hla.rti1516e.AttributeHandle;
import hla.rti1516e.AttributeHandleValueMap;
import hla.rti1516e.FederateHandleSet;
import hla.rti1516e.InteractionClassHandle;
import hla.rti1516e.LogicalTime;
import hla.rti1516e.NullFederateAmbassador;
import hla.rti1516e.ObjectClassHandle;
import hla.rti1516e.ObjectInstanceHandle;
import hla.rti1516e.OrderType;
import hla.rti1516e.ParameterHandle;
import hla.rti1516e.ParameterHandleValueMap;
import hla.rti1516e.SynchronizationPointFailureReason;
import hla.rti1516e.TransportationTypeHandle;
import hla.rti1516e.encoding.DecoderException;
import hla.rti1516e.encoding.HLAinteger16BE;
import hla.rti1516e.encoding.HLAinteger32BE;
import hla.rti1516e.exceptions.FederateInternalError;
import hla.rti1516e.time.HLAfloat64Time;
import org.portico.impl.hla1516e.types.encoding.HLA1516eInteger16BE;
import org.portico.impl.hla1516e.types.encoding.HLA1516eInteger32BE;

/**
 * This class handles all incoming callbacks from the RTI regarding a particular
 * {@link StolikFederate}. It will log information about any callbacks it
 * receives, thus demonstrating how to deal with the provided callback information.
 */
public class StolikFederateAmbassador extends NullFederateAmbassador
{
    //----------------------------------------------------------
    //                    STATIC VARIABLES
    //----------------------------------------------------------

    //----------------------------------------------------------
    //                   INSTANCE VARIABLES
    //----------------------------------------------------------
    private StolikFederate federate;

    // these variables are accessible in the package
    protected double federateTime        = 0.0;
    protected double federateLookahead   = 1.0;

    protected boolean isRegulating       = false;
    protected boolean isConstrained      = false;
    protected boolean isAdvancing        = false;

    protected boolean isAnnounced        = false;
    protected boolean isReadyToRun       = false;

    protected boolean isRunning       = true;

    //----------------------------------------------------------
    //                      CONSTRUCTORS
    //----------------------------------------------------------

    public StolikFederateAmbassador(StolikFederate federate )
    {
        this.federate = federate;
    }

    //----------------------------------------------------------
    //                    INSTANCE METHODS
    //----------------------------------------------------------
    private void log( String message )
    {
        System.out.println( "FederateAmbassador: " + message );
    }

    //////////////////////////////////////////////////////////////////////////
    ////////////////////////// RTI Callback Methods //////////////////////////
    //////////////////////////////////////////////////////////////////////////
    @Override
    public void synchronizationPointRegistrationFailed( String label,
                                                        SynchronizationPointFailureReason reason )
    {
        log( "Failed to register sync point: " + label + ", reason="+reason );
    }

    @Override
    public void synchronizationPointRegistrationSucceeded( String label )
    {
        log( "Successfully registered sync point: " + label );
    }

    @Override
    public void announceSynchronizationPoint( String label, byte[] tag )
    {
        log( "Synchronization point announced: " + label );
        if( label.equals(StolikFederate.READY_TO_RUN) )
            this.isAnnounced = true;
    }

    @Override
    public void federationSynchronized( String label, FederateHandleSet failed )
    {
        log( "Federation Synchronized: " + label );
        if( label.equals(StolikFederate.READY_TO_RUN) )
            this.isReadyToRun = true;
    }

    /**
     * The RTI has informed us that time regulation is now enabled.
     */
    @Override
    public void timeRegulationEnabled( LogicalTime time )
    {
        this.federateTime = ((HLAfloat64Time)time).getValue();
        this.isRegulating = true;
    }

    @Override
    public void timeConstrainedEnabled( LogicalTime time )
    {
        this.federateTime = ((HLAfloat64Time)time).getValue();
        this.isConstrained = true;
    }

    @Override
    public void timeAdvanceGrant( LogicalTime time )
    {
        this.federateTime = ((HLAfloat64Time)time).getValue();
        this.isAdvancing = false;
    }

    @Override
    public void discoverObjectInstance( ObjectInstanceHandle theObject,
                                        ObjectClassHandle theObjectClass,
                                        String objectName )
            throws FederateInternalError
    {
        log( "Discoverd Object: handle=" + theObject + ", classHandle=" +
                theObjectClass + ", name=" + objectName );
    }

    @Override
    public void reflectAttributeValues( ObjectInstanceHandle theObject,
                                        AttributeHandleValueMap theAttributes,
                                        byte[] tag,
                                        OrderType sentOrder,
                                        TransportationTypeHandle transport,
                                        SupplementalReflectInfo reflectInfo )
            throws FederateInternalError
    {
        // just pass it on to the other method for printing purposes
        // passing null as the time will let the other method know it
        // it from us, not from the RTI
        reflectAttributeValues( theObject,
                theAttributes,
                tag,
                sentOrder,
                transport,
                null,
                sentOrder,
                reflectInfo );
    }

    @Override
    public void reflectAttributeValues( ObjectInstanceHandle theObject,
                                        AttributeHandleValueMap theAttributes,
                                        byte[] tag,
                                        OrderType sentOrdering,
                                        TransportationTypeHandle theTransport,
                                        LogicalTime time,
                                        OrderType receivedOrdering,
                                        SupplementalReflectInfo reflectInfo )
            throws FederateInternalError
    {
        StringBuilder builder = new StringBuilder( "Reflection for object:" );

        // print the handle
        builder.append( " handle=" + theObject );
        // print the tag
        builder.append( ", tag=" + new String(tag) );
        // print the time (if we have it) we'll get null if we are just receiving
        // a forwarded call from the other reflect callback above


        // print the attribute information
        builder.append( ", attributeCount=" + theAttributes.size() );
        builder.append( "\n" );
        for( AttributeHandle attributeHandle : theAttributes.keySet() )
        {
            // print the attibute handle
            builder.append( "\tattributeHandle=" );

            // if we're dealing with Flavor, decode into the appropriate enum value

            builder.append( "\n" );
        }

        log( builder.toString() );
    }

    @Override
    public void receiveInteraction( InteractionClassHandle interactionClass,
                                    ParameterHandleValueMap theParameters,
                                    byte[] tag,
                                    OrderType sentOrdering,
                                    TransportationTypeHandle theTransport,
                                    SupplementalReceiveInfo receiveInfo )
            throws FederateInternalError
    {
        // just pass it on to the other method for printing purposes
        // passing null as the time will let the other method know it
        // it from us, not from the RTI
        this.receiveInteraction( interactionClass,
                theParameters,
                tag,
                sentOrdering,
                theTransport,
                null,
                sentOrdering,
                receiveInfo );
    }

    @Override
    public void receiveInteraction( InteractionClassHandle interactionClass,
                                    ParameterHandleValueMap theParameters,
                                    byte[] tag,
                                    OrderType sentOrdering,
                                    TransportationTypeHandle theTransport,
                                    LogicalTime time,
                                    OrderType receivedOrdering,
                                    SupplementalReceiveInfo receiveInfo )
            throws FederateInternalError
    {
        StringBuilder builder = new StringBuilder( "Interaction Received:" );

        // print the handle
        builder.append( " handle=" + interactionClass );
        if( interactionClass.equals(federate.zajmijStolikHandle) )
        {
            builder.append( " (zajmij_stolik)" );
            byte[] bytes = theParameters.get(federate.idKlientaZajmijHandle);
            byte[] bytes2 = theParameters.get(federate.nrStolikaZajmijHandle);
            HLAinteger32BE nr_stolika = new HLA1516eInteger32BE();
            HLAinteger32BE id_klienta = new HLA1516eInteger32BE();
            try {
                id_klienta.decode(bytes);
                nr_stolika.decode(bytes2);
            } catch (DecoderException e) {
                e.printStackTrace();
            }
            for (int i = 0; i<federate.getMaxStoliki(); i++){
                if(federate.stoliki.get(i).getDostepnosc() == true){
                    federate.stoliki.get(i).zajmij_stolik(nr_stolika.getValue(), id_klienta.getValue());
                    break;
                }
            }

        }
        else if( interactionClass.equals(federate.zwolnijStolikHandle) )
        {
            byte[] bytes = theParameters.get(federate.idKlientaZwolnijHandle);
            byte[] bytes2 = theParameters.get(federate.nrStolikaZwolnijHandle);
            HLAinteger32BE id_klienta = new HLA1516eInteger32BE();
            HLAinteger32BE nr_stolika = new HLA1516eInteger32BE();
            try {
                id_klienta.decode(bytes);
                nr_stolika.decode(bytes2);
            } catch (DecoderException e) {
                e.printStackTrace();
            }
            for (int i = 0; i<federate.getMaxStoliki(); i++){
                if(federate.stoliki.get(i).nr_stolika == nr_stolika.getValue()){
                    federate.stoliki.get(i).zwolnij_stolik(nr_stolika.getValue(), id_klienta.getValue());
                    break;
                }
            }
            builder.append( " (zwolnij_stolik)" );
        }
        else if(interactionClass.equals(federate.obsluzStolikHandle))
        {
            byte[] bytes = theParameters.get(federate.nrStolikObsluzHandle);
            byte[] bytes2 = theParameters.get(federate.czasObslugiHandle);
            HLAinteger32BE nr_stolika = new HLA1516eInteger32BE();
            HLAinteger32BE czas_obslugi = new HLA1516eInteger32BE();
            try {
                nr_stolika.decode(bytes);
                czas_obslugi.decode(bytes2);
            } catch (DecoderException e){
                e.printStackTrace();
            }

        }

        // print the tag
        builder.append( ", tag=" + new String(tag) );
        // print the time (if we have it) we'll get null if we are just receiving
        // a forwarded call from the other reflect callback above
        if( time != null )
        {
            builder.append( ", time=" + ((HLAfloat64Time)time).getValue() );
        }

        // print the parameer information
        builder.append( ", parameterCount=" + theParameters.size() );
        builder.append( "\n" );
//        for( ParameterHandle parameter : theParameters.keySet() )
//        {
//
//            if(parameter.equals(federate.idKlientaHandle))
//            {
//                builder.append( "\tCOUNT PARAM!" );
//                //byte[] bytes = theParameters.get(federate.stolikHandle);
//
//
//                //int nrValue = nr_stolika.getValue();
//                int idValue = id_klienta.getValue();
//                if( interactionClass.equals(federate.zajmijStolikHandle) )
//                {
//                    Stolik.getInstance().zajmij_stolik(1, idValue);
//                }
//                else if( interactionClass.equals(federate.zwolnijStolikHandle) )
//                {
//                    Stolik.getInstance().zwolnij_stolik(1, idValue);
//                }
//
//
//            }
//            else
//            {
//                // print the parameter handle
//                builder.append( "\tparamHandle=" );
//                builder.append( parameter );
//                // print the parameter value
//                builder.append( ", paramValue=" );
//                builder.append( theParameters.get(parameter).length );
//                builder.append( " bytes" );
//                builder.append( "\n" );
//            }
//        }

        log( builder.toString() );
    }

    @Override
    public void removeObjectInstance( ObjectInstanceHandle theObject,
                                      byte[] tag,
                                      OrderType sentOrdering,
                                      SupplementalRemoveInfo removeInfo )
            throws FederateInternalError
    {
        log( "Object Removed: handle=" + theObject );
    }

    //----------------------------------------------------------
    //                     STATIC METHODS
    //----------------------------------------------------------
}
