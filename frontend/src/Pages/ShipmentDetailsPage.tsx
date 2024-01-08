import React, { useState } from 'react'
import NormalNavBar from '../Components/NormalNavBar'
import { useAuth } from '../auth/components/AuthService'
import axios from 'axios'
import { useEffect } from 'react'
import Cookies from 'js-cookie'
import { toast } from 'react-toastify'
import { Link } from 'react-router-dom'
import { useLocation } from 'react-router-dom';

axios.defaults.withCredentials = true
function ShipmentDetailsPage() {

    const auth = useAuth()

    let location = useLocation();
    let params = new URLSearchParams(location.search);
    let shipmentId = params.get('shipmentId');

    interface Address {
        addressId: string;
        streetAddress: string;
        city: string;
        country: string;
        postalCode: string;
    }

    interface Shipment {
        shipmentId: string;
        status: string;
        expectedMovingDate: string;
        actualMovingDate: string;
        shipmentName: string;
        approximateWeight: number;
        weight: number;
        pickupAddress: Address;
        destinationAddress: Address;
        vin: string;
        userId: string;
        email: string;
        phoneNumber: string;
        firstName: string;
        lastName: string;
    }

    const [selectedShipment, setSelectedShipment] = useState<Shipment>({
        shipmentId: '',
        status: '',
        expectedMovingDate: '',
        actualMovingDate: '',
        shipmentName: '',
        approximateWeight: 0,
        weight: 0,
        pickupAddress: {
            addressId: '',
            streetAddress: '',
            city: '',
            country: '',
            postalCode: '',
        },
        destinationAddress: {
            addressId: '',
            streetAddress: '',
            city: '',
            country: '',
            postalCode: '',
        },
        vin: '',
        userId: '',
        email: '',
        phoneNumber: '',
        firstName: '',
        lastName: '',
    });


    const [displayDetail, setDisplayDetail] = useState(false)

    const getShipmentDetails = async (shipmentId: string) => {
        setDisplayDetail(true)
        console.log(shipmentId);

        try {
            const response = await axios.get(`http://localhost:8080/api/v1/movingexpress/shipments/${shipmentId}`, {});

            var data = response.data;

            const shipmentDetail: Shipment = {
                shipmentId: data.shipmentId,
                status: data.status,
                expectedMovingDate: data.expectedMovingDate,
                actualMovingDate: data.actualMovingDate,
                shipmentName: data.shipmentName,
                approximateWeight: data.approximateWeight,
                weight: data.weight,
                pickupAddress: data.pickupAddress,
                destinationAddress: data.destinationAddress,
                vin: '',
                userId: '',
                email: data.email,
                phoneNumber: data.phoneNumber,
                firstName: data.firstName,
                lastName: data.lastName
            };

            console.log(shipmentDetail)
            setSelectedShipment(shipmentDetail)

        } catch (error) {
            toast.error('Error Loading Data', {
                position: 'top-right',
                autoClose: 5000,
                hideProgressBar: false,
                //closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: 'light',
            })
        }
    }

    const declineShipment = async (shipmentId: string) => {

    };

    //handle accept or delete shipment
    const acceptShipment = async (shipmentId: string) => {

    }

    useEffect(() => {
        console.log(shipmentId)

        if (shipmentId) {
            console.log(shipmentId)
            getShipmentDetails(shipmentId);
        }
    }, [shipmentId]);

    const containerStyle = {
        display: 'flex' as 'flex',
        flexDirection: 'column' as 'column',
        minHeight: '72.8vh',
    };


    return (
        <div style={containerStyle}>
            <NormalNavBar />

            <div className='relative w-full h-100 flex justify-center bg-white opacity-100 divide-transparent rounded-lg'>
                <div className='flex flex-col w-[70%]'>
                    <div className='flex flex-row items-center justify-between mt-6 mb-5'>
                        <div style={{ fontFamily: 'Bebas Neue, cursive' }} className='text-2xl'>Shipment <span className="text-companyYellow">Details</span></div>
                        <div className='flex flex-row'>
                            <div className='mx-5'>
                                <button id='CloseDetail' className='px-2.5 py-1 bg-[#949494] text-white rounded-sm' onClick={() => { setDisplayDetail(false) }}>Print Shipment Report</button>
                            </div>
                            <div>
                                <button id='CloseDetail' className='px-2.5 py-1 bg-[#FF0000] text-white rounded-sm' onClick={() => { setDisplayDetail(false) }}>Cancel Shipment</button>
                            </div>
                        </div>
                    </div>

                    <hr className="border-1 border-grey mb-6" />

                    <div className='flex flex-row gap-4'>
                        <div className="flex flex-col gap-1 w-[26.5%]">
                            <input
                                type="text"
                                className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm"
                                id="DetailShipmentId"
                                name="DetailShipmentId"
                                required

                                value={selectedShipment.shipmentId || ''}
                            />
                            <label
                                style={{
                                    fontFamily:
                                        'Bebas Neue, cursive',
                                }}
                                className="text-[#696969] text-xs"
                            >
                                Shipment ID
                            </label>
                        </div>
                        <div className="flex flex-col gap-1">
                            <input
                                type="text"
                                className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm"
                                id="ShipmentName"
                                name="ShipmentName"
                                required

                                value={selectedShipment.shipmentName || ''}
                            />
                            <label
                                style={{
                                    fontFamily:
                                        'Bebas Neue, cursive',
                                }}
                                className="text-[#696969] text-xs"
                            >
                                Shipment Name
                            </label>
                        </div>
                        <div className="flex flex-col gap-1">
                            <input
                                type="text"
                                className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm"
                                id="FullName"
                                name="FullName"
                                required
                                value={`${selectedShipment.firstName || ''} ${selectedShipment.lastName || ''}`}
                            />
                            <label
                                style={{
                                    fontFamily:
                                        'Bebas Neue, cursive',
                                }}
                                className="text-[#696969] text-xs"
                            >
                                Full Name
                            </label>
                        </div>
                        <div className="flex flex-col gap-1">
                            <input
                                type="text"
                                className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm"
                                id="Email"
                                name="Email"
                                required

                                value={selectedShipment.email || ''}
                            />
                            <label
                                style={{
                                    fontFamily:
                                        'Bebas Neue, cursive',
                                }}
                                className="text-[#696969] text-xs"
                            >
                                Email
                            </label>
                        </div>
                    </div>
                    <div className='flex flex-row gap-4'>
                        <div className="flex flex-col gap-1">
                            <input
                                type="text"
                                className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm"
                                id="PhoneNumber"
                                name="PhoneNumber"
                                required

                                value={selectedShipment.phoneNumber || ''}
                            />
                            <label
                                style={{
                                    fontFamily:
                                        'Bebas Neue, cursive',
                                }}
                                className="text-[#696969] text-xs"
                            >
                                Phone Number
                            </label>
                        </div>
                        <div className="flex flex-col gap-1">
                            <input
                                type="text"
                                className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm"
                                id="ExpectedMovingDate"
                                name="ExpectedMovingDate"
                                required

                                value={selectedShipment.expectedMovingDate || ''}
                            />
                            <label
                                style={{
                                    fontFamily:
                                        'Bebas Neue, cursive',
                                }}
                                className="text-[#696969] text-xs"
                            >
                                Expected Moving Date
                            </label>
                        </div>
                    </div>

                    <div className='flex flex-row gap-4'>
                        <div className="flex flex-col gap-1 w-[26.5%]">
                            <input
                                type="text"
                                className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm"
                                id="PickupAddress"
                                name="PickupAddress"
                                required

                                value={`${selectedShipment.pickupAddress.streetAddress || ''}, ${selectedShipment.pickupAddress.city || ''}, ${selectedShipment.pickupAddress.postalCode || ''}, ${selectedShipment.pickupAddress.country || ''}`}
                            />
                            <label
                                style={{
                                    fontFamily:
                                        'Bebas Neue, cursive',
                                }}
                                className="text-[#696969] text-xs"
                            >
                                Pickup Address
                            </label>
                        </div>

                        <div className="flex flex-col gap-1 w-[26.5%]">
                            <input
                                type="text"
                                className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm"
                                id="DestinationAddress"
                                name="DestinationAddress"
                                required

                                value={`${selectedShipment.destinationAddress.streetAddress || ''}, ${selectedShipment.destinationAddress.city || ''}, ${selectedShipment.destinationAddress.postalCode || ''}, ${selectedShipment.destinationAddress.country || ''}`}
                            />
                            <label
                                style={{
                                    fontFamily:
                                        'Bebas Neue, cursive',
                                }}
                                className="text-[#696969] text-xs"
                            >
                                Destination Address
                            </label>
                        </div>
                    </div>
                    <div className='flex flex-row gap-4'>
                        <div className="flex flex-col gap-1 w-[26.5%]">
                            <input
                                type="text"
                                className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm"
                                id="ShipmentStatus"
                                name="ShipmentStatus"
                                required
                                value={selectedShipment.status || ''}
                            />
                            <label
                                style={{
                                    fontFamily:
                                        'Bebas Neue, cursive',
                                }}
                                className="text-[#696969] text-xs"
                            >
                                Shipment Status
                            </label>
                        </div>

                        <div className="flex flex-col gap-1 w-[26.5%]">
                            <input
                                type="text"
                                className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm"
                                id="ApproximateWeight"
                                name="ApproximateWeight"
                                required
                                value={selectedShipment.approximateWeight}
                            />
                            <label
                                style={{
                                    fontFamily:
                                        'Bebas Neue, cursive',
                                }}
                                className="text-[#696969] text-xs"
                            >
                                Approximate Weight
                            </label>
                        </div>
                    </div>



                    <div className="flex flex-row gap-1 justify-end mb-5">
                        {selectedShipment.status !== "DECLINED" ?
                            <div className=' flex flex-row gap-3'>
                                <div><button id='acceptBtn' onClick={() => { acceptShipment(selectedShipment.shipmentId) }} className='px-2.5 py-1 bg-green-500 text-white rounded-sm'>Accept</button></div>
                            </div>
                            : <div> </div>
                        }
                    </div>
                </div>
            </div>
        </div>
    );
}


export default ShipmentDetailsPage;