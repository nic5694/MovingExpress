import React from 'react'

function ShipmentQuoteDetails(props:any) {

    interface Quote {
        quoteId: string;
        quoteStatus: string;
        pickupStreetAddress: string;
        pickupCity: string;
        pickupCountry: string;
        pickupPostalCode: string;
        pickupNumberOfRooms: number;
        pickupElevator: boolean;
        pickupBuildingType: string;
        destinationStreetAddress: string;
        destinationCity: string;
        destinationCountry: string;
        destinationPostalCode: string;
        destinationNumberOfRooms: number;
        destinationElevator: boolean;
        destinationBuildingType: string;
        firstName: string;
        lastName: string;
        emailAddress: string;
        phoneNumber: string;
        contactMethod: string;
        expectedMovingDate: string;
        initiationDate: string;
        comment: string;
        name: string;
      }

    let quoteDetails : Quote = props.shipmentQuote

  return (
    <div className=" overflow-y-scroll pr-10 h-[70vh]">

        <div style={{ fontFamily: 'Bebas Neue, cursive' }} className='text-sm mb-3 bg-companyYellow pl-2 text-white py-1'>Personal Information</div>

        <div className='flex gap-3 flex-wrap'>
            <div className="flex flex-col gap-1">
                <input
                    type="text"
                    className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm"
                    id="DetailName"
                    name="DetailName"
                    readOnly
                    value={quoteDetails.name || ''}
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
                    className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm w-[]"
                    id="DetailQuoteId"
                    name="DetailQuoteId"
                    readOnly
                    value={quoteDetails.quoteId || ''}
                />
                <label
                    style={{
                    fontFamily:
                        'Bebas Neue, cursive',
                    }}
                    className="text-[#696969] text-xs"
                >
                    Quote ID
                </label>
            </div>

            <div className="flex flex-col gap-1">
                <input
                    type="text"
                    className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm w-[]"
                    id="DetailFirstName"
                    name="DetailFirstName"
                    readOnly
                    value={quoteDetails.firstName || ''}
                />
                <label
                    style={{
                    fontFamily:
                        'Bebas Neue, cursive',
                    }}
                    className="text-[#696969] text-xs"
                >
                   first name
                </label>
            </div>

            <div className="flex flex-col gap-1">
                <input
                    type="text"
                    className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm w-[]"
                    id="DetailLastName"
                    name="DetailLastName"
                    readOnly
                    value={quoteDetails.lastName || ''}
                />
                <label
                    style={{
                    fontFamily:
                        'Bebas Neue, cursive',
                    }}
                    className="text-[#696969] text-xs"
                >
                    Last Name
                </label>
            </div>

            <div className="flex flex-col gap-1">
                <input
                    type="text"
                    className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm w-[]"
                    id="DetailEmail"
                    name="DetailEmail"
                    readOnly
                    value={quoteDetails.emailAddress || ''}
                />
                <label
                    style={{
                    fontFamily:
                        'Bebas Neue, cursive',
                    }}
                    className="text-[#696969] text-xs"
                >
                   E-mail
                </label>
            </div>

            <div className="flex flex-col gap-1">
                <input
                    type="text"
                    className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm w-[]"
                    id="DetailPhoneNumber"
                    name="DetailPhoneNumber"
                    readOnly
                    value={quoteDetails.phoneNumber || ''}
                />
                <label
                    style={{
                    fontFamily:
                        'Bebas Neue, cursive',
                    }}
                    className="text-[#696969] text-xs"
                >
                    phone number
                </label>
            </div>

            <div className="flex flex-col gap-1">
                <input
                    type="text"
                    className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm"
                    id="ExpectedMovingDate"
                    name="ExpectedMovingDate"
                    readOnly
                    value={quoteDetails.expectedMovingDate || ''}
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

            <div className='flex flex-col'>
                <div className='text-[10px] font-bold opacity-70'>Contact Method</div>
                <div className="flex items-center gap-2">
                    <input
                    type="radio"
                    className="border border-[lightgray] text-xs h-[35px] rounded-sm"
                    id="PreferredEmail"
                    name="PreferredEmail"
                    required
                    readOnly
                    checked={quoteDetails.contactMethod === 'EMAIL'}
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

                    <input
                    type="radio"
                    className="border border-[lightgray] text-xs h-[35px] rounded-sm"
                    id="PreferredPhoneNumber"
                    name="PreferredPhoneNumber"
                    required
                    readOnly
                    checked={quoteDetails.contactMethod === 'PHONE_NUMBER'}
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

                    <input
                    type="radio"
                    className="border border-[lightgray] text-xs h-[35px] rounded-sm"
                    id="PreferredBoth"
                    name="PreferredBoth"
                    required
                    readOnly
                    checked={quoteDetails.contactMethod === 'BOTH'}
                    />
                    <label
                    style={{
                        fontFamily:
                        'Bebas Neue, cursive',
                    }}
                    className="text-[#696969] text-xs"
                    >
                    Both
                    </label>
                </div>
            </div>    

        </div>
        
        <div className="flex flex-col gap-1 mt-3">

            <textarea  className='border text-xs py-2 px-2' value={quoteDetails.comment || ''} readOnly></textarea>
            <label
            style={{
                fontFamily:
                'Bebas Neue, cursive',
            }}
            className="text-[#696969] text-xs"
            >
            Additionnal Comments
            </label>
        </div>

        {/* -------------------------------------------------------------------- */}

        <div style={{ fontFamily: 'Bebas Neue, cursive' }} className='text-sm mt-8 mb-3 bg-companyYellow pl-2 text-white py-1'>Pick Up Location</div>

        <div className='flex gap-3 flex-wrap'>
            <div className="flex flex-col gap-1">
                  <input
                    type="text"
                    className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm"
                    id="PickupAddress"
                    name="PickupAddress"
                    required
                    readOnly
                    value={quoteDetails.pickupStreetAddress + ", " + quoteDetails.pickupCountry  || ''}
                  />
                  <label
                    style={{
                      fontFamily:
                        'Bebas Neue, cursive',
                    }}
                    className="text-[#696969] text-xs"
                  >
                    Address
                  </label>
            </div>

            <div className="flex flex-col gap-1">
                <input
                    type="text"
                    className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm"
                    id="PickupCity"
                    name="PickupCity"
                    required
                    readOnly
                    value={quoteDetails.pickupCity || ''}
                />
                <label
                    style={{
                    fontFamily:
                        'Bebas Neue, cursive',
                    }}
                    className="text-[#696969] text-xs"
                >
                    City
                </label>
            </div>
           
            <div className="flex flex-col gap-1">
                <input
                    type="text"
                    className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm"
                    id="PickupPostalCode"
                    name="PickupPostalCode"
                    required
                    readOnly
                    value={quoteDetails.pickupPostalCode || ''}
                />
                <label
                    style={{
                    fontFamily:
                        'Bebas Neue, cursive',
                    }}
                    className="text-[#696969] text-xs"
                >
                    Postal Code
                </label>
            </div>

            <div className="flex flex-col gap-1">
                <input
                    type="text"
                    className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm"
                    id="PickupBuildingType"
                    name="PickupBuildingType"
                    required
                    readOnly
                    value={quoteDetails.pickupBuildingType || ''}
                />
                <label
                    style={{
                    fontFamily:
                        'Bebas Neue, cursive',
                    }}
                    className="text-[#696969] text-xs"
                >
                    Building Type
                </label>
            </div>

            <div className="flex flex-col gap-1">
                <input
                    type="text"
                    className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm"
                    id="PickupNumberOfRooms"
                    name="PickupNumberOfRooms"
                    required
                    readOnly
                    value={quoteDetails.pickupNumberOfRooms || ''}
                />
                <label
                    style={{
                    fontFamily:
                        'Bebas Neue, cursive',
                    }}
                    className="text-[#696969] text-xs"
                >
                    No. of Rooms
                </label>
            </div>

            <div className='flex flex-col'>
                <div className='text-[10px] font-bold opacity-70'>Is there an elevator?</div>
                <div className="flex items-center gap-2">
                <input
                    type="radio"
                    className="border border-[lightgray] text-xs h-[35px] rounded-sm"
                    id="PickupYes"
                    name="PickupYes"
                    required
                    readOnly
                    checked={quoteDetails.pickupElevator === true}
                />
                <label
                    style={{
                    fontFamily:
                        'Bebas Neue, cursive',
                    }}
                    className="text-[#696969] text-xs"
                >
                    Yes
                </label>

                <input
                    type="radio"
                    className="border border-[lightgray] text-xs h-[35px] rounded-sm"
                    id="PickupNo"
                    name="PickupNo"
                    required
                    readOnly
                    checked={quoteDetails.pickupElevator === false}
                />
                <label
                    style={{
                    fontFamily:
                        'Bebas Neue, cursive',
                    }}
                    className="text-[#696969] text-xs"
                >
                    No
                </label>
                </div>
            </div>
                  
        </div>

        {/* -------------------------------------------------------------------- */}


        <div style={{ fontFamily: 'Bebas Neue, cursive' }} className='text-sm mt-5 mb-3 bg-companyYellow pl-2 text-white py-1'>Drop Off Destination</div>

        <div className='flex gap-3 flex-wrap'>

            <div className="flex flex-col gap-1">
                <input
                type="text"
                className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm"
                id="DestinationAddress"
                name="DestinationAddress"
                required
                readOnly
                value={`${quoteDetails.destinationStreetAddress || ''}, ${quoteDetails.destinationCountry || ''}`}
                />
                <label
                style={{
                    fontFamily:
                    'Bebas Neue, cursive',
                }}
                className="text-[#696969] text-xs"
                >
                Address
                </label>
            </div>

            <div className="flex flex-col gap-1">
            <input
                type="text"
                className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm"
                id="DestinationCity"
                name="DestinationCity"
                required
                readOnly
                value={quoteDetails.destinationCity || ''}
            />
            <label
                style={{
                fontFamily:
                    'Bebas Neue, cursive',
                }}
                className="text-[#696969] text-xs"
            >
                City
            </label>
            </div>

            <div className="flex flex-col gap-1">
            <input
                type="text"
                className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm"
                id="DestinationPostalCode"
                name="DestinationPostalCode"
                required
                readOnly
                value={quoteDetails.destinationPostalCode || ''}
            />
            <label
                style={{
                fontFamily:
                    'Bebas Neue, cursive',
                }}
                className="text-[#696969] text-xs"
            >
                Postal Code
            </label>
            </div>

            <div className="flex flex-col gap-1">
            <input
                type="text"
                className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm"
                id="DestinationBuildingType"
                name="DestinationBuildingType"
                required
                readOnly
                value={quoteDetails.destinationBuildingType || ''}
            />
            <label
                style={{
                fontFamily:
                    'Bebas Neue, cursive',
                }}
                className="text-[#696969] text-xs"
            >
                Building Type
            </label>
            </div>
            
            <div className="flex flex-col gap-1">
                    <input
                      type="text"
                      className="border border-[lightgray] text-xs h-[35px] px-4 rounded-sm"
                      id="DestinationNumberOfRooms"
                      name="DestinationNumberOfRooms"
                      required
                      readOnly
                      value={quoteDetails.destinationNumberOfRooms || ''}
                    />
                    <label
                      style={{
                        fontFamily:
                          'Bebas Neue, cursive',
                      }}
                      className="text-[#696969] text-xs"
                    >
                      No. of Rooms
                    </label>
            </div>

            <div className='flex flex-col mb-5'>
                  <div className='text-[10px] font-bold opacity-70'>Is there an elevator?</div>
                  <div className="flex items-center gap-2">
                    <input
                      type="radio"
                      className="border border-[lightgray] text-xs h-[35px] rounded-sm"
                      id="DestinationYes"
                      name="DestinationYes"
                      required
                      readOnly
                      checked={quoteDetails.destinationElevator === true}
                    />
                    <label
                      style={{
                        fontFamily:
                          'Bebas Neue, cursive',
                      }}
                      className="text-[#696969] text-xs"
                    >
                      Yes
                    </label>

                    <input
                      type="radio"
                      className="border border-[lightgray] text-xs h-[35px] rounded-sm"
                      id="DestinationNo"
                      name="DestinationNo"
                      required
                      readOnly
                      checked={quoteDetails.destinationElevator === false}
                    />
                    <label
                      style={{
                        fontFamily:
                          'Bebas Neue, cursive',
                      }}
                      className="text-[#696969] text-xs"
                    >
                      No
                    </label>
                  </div>
            </div>

        </div>

    </div>
  )
}

export default ShipmentQuoteDetails