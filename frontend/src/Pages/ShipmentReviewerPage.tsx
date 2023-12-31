import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import logo from '../Images/ME_Logo.png'
import axios from 'axios'
import { toast } from 'react-toastify'
import ShipmentQuoteDetails from '../Components/ShipmentQuoteDetails'

function ShipmentReviewerPage() {
  const [menuOpen, setMenuOpen] = useState(false)
  const [pendingQuotes, setPendingQuotes] = useState([])
  const [declinedQuotes, setDeclinedQuotes] = useState([])

  const [displayDetail, setDisplayDetail] = useState(false)

  const fetchData = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/v1/movingexpress/quotes', {
        params: {
          quoteStatus: 'PENDING',
        },
      });

      console.log(response)

      //@ts-ignore
      const mappedQuoteForms = response.data.map((item: any) => ({
        quoteId: item.quoteId,
        quoteStatus: item.quoteStatus,
        pickupStreetAddress: item.pickUpAddress,
        pickupCity: item.cityP,
        pickupCountry: item.countryP,
        pickupPostalCode: item.postalCodeP,
        pickupNumberOfRooms: item.numberofRoomP,
        pickupElevator: item.elevatorisPresentP,
        pickupBuildingType: item.buildingTypeP,
        destinationStreetAddress: item.dropOffAddress,
        destinationCity: item.cityD,
        destinationCountry: item.countryD,
        destinationPostalCode: item.postalCodeD,
        destinationNumberOfRooms: item.numberofRoomD,
        destinationElevator: item.elevatorisPresentD,
        destinationBuildingType: item.buildingTypeD,
        firstName: item.firstName,
        lastName: item.lastName,
        emailAddress: item.emailAddress,
        phoneNumber: item.phoneNumber,
        contactMethod: item.wayToContact,
        expectedMovingDate: item.movingDate,
        comment: item.additionalComments,
        shipmentName: item.name,
      }));

      setPendingQuotes(mappedQuoteForms)

    } catch (error) {
      toast.error('Error Loading Data', {
        position: 'top-right',
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: 'light',
      })
    }

    try {
      const response = await axios.get('http://localhost:8080/api/v1/movingexpress/quotes', {
        params: {
          quoteStatus: 'DECLINED',
        },
      });

      console.log(response)

      //@ts-ignore
      const mappedQuoteForms = response.data.map((item: any) => ({
        quoteId: item.quoteId,
        quoteStatus: item.quoteStatus,
        pickupStreetAddress: item.pickUpAddress,
        pickupCity: item.cityP,
        pickupCountry: item.countryP,
        pickupPostalCode: item.postalCodeP,
        pickupNumberOfRooms: item.numberofRoomP,
        pickupElevator: item.elevatorisPresentP,
        pickupBuildingType: item.buildingTypeP,
        destinationStreetAddress: item.dropOffAddress,
        destinationCity: item.cityD,
        destinationCountry: item.countryD,
        destinationPostalCode: item.postalCodeD,
        destinationNumberOfRooms: item.numberofRoomD,
        destinationElevator: item.elevatorisPresentD,
        destinationBuildingType: item.buildingTypeD,
        firstName: item.firstName,
        lastName: item.lastName,
        emailAddress: item.emailAddress,
        phoneNumber: item.phoneNumber,
        contactMethod: item.wayToContact,
        expectedMovingDate: item.movingDate,
        comment: item.additionalComments,
        shipmentName: item.name,
      }));

      setDeclinedQuotes(mappedQuoteForms)

    } catch (error) {
      toast.error('Error Loading Data', {
        position: 'top-right',
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: 'light',
      })
    }

  }

  useEffect(() => {
    fetchData();
  }, [])

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

  const [selectedQuote, setSelectedQuote] = useState<Quote>({
    quoteId: '',
    quoteStatus: '',
    pickupStreetAddress: '',
    pickupCity: '',
    pickupCountry: '',
    pickupPostalCode: '',
    pickupNumberOfRooms: 0,
    pickupElevator: false,
    pickupBuildingType: '',
    destinationStreetAddress: '',
    destinationCity: '',
    destinationCountry: '',
    destinationPostalCode: '',
    destinationNumberOfRooms: 0,
    destinationElevator: false,
    destinationBuildingType: '',
    firstName: '',
    lastName: '',
    emailAddress: '',
    phoneNumber: '',
    contactMethod: '',
    expectedMovingDate: '',
    initiationDate: '',
    comment: '',
    name: '',
  });

  useEffect(() => {
    if (displayDetail) {
      document.body.style.overflow = 'hidden';
    } else {
      document.body.style.overflow = 'auto';
    }
  }, [displayDetail]);

  const getQuoteDetails = async (quoteId: string) => {
    setDisplayDetail(true)

    console.log(quoteId);

    try {
      const response = await axios.get(`http://localhost:8080/api/v1/movingexpress/quotes/${quoteId}`, {});

      var data = response.data;

      const quoteDetail: Quote = {
        quoteId: data.quoteId,
        quoteStatus: data.quoteStatus,
        pickupStreetAddress: data.pickupStreetAddress,
        pickupCity: data.pickupCity,
        pickupCountry: data.pickupCountry,
        pickupPostalCode: data.pickupPostalCode,
        pickupNumberOfRooms: data.pickupNumberOfRooms,
        pickupElevator: data.pickupElevator,
        pickupBuildingType: data.pickupBuildingType,
        destinationStreetAddress: data.destinationStreetAddress,
        destinationCity: data.destinationCity,
        destinationCountry: data.destinationCountry,
        destinationPostalCode: data.destinationPostalCode,
        destinationNumberOfRooms: data.destinationNumberOfRooms,
        destinationElevator: data.destinationElevator,
        destinationBuildingType: data.destinationBuildingType,
        firstName: data.firstName,
        lastName: data.lastName,
        emailAddress: data.emailAddress,
        phoneNumber: data.phoneNumber,
        contactMethod: data.contactMethod,
        expectedMovingDate: data.expectedMovingDate,
        initiationDate: data.initiationDate,
        comment: data.comment,
        name: data.name,
      };

      console.log(quoteDetail)
      setSelectedQuote(quoteDetail)

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

  const declineQuote = async (quoteId: string) => {
    try {
      const response = await axios.post(
        `http://localhost:8080/api/v1/movingexpress/quotes/${quoteId}/events`,
        {
          event: 'decline'
        }
      );

      console.log('Response:', response.data);

      setDisplayDetail(false)
      fetchData()

    } catch (error) {
      console.error('Error:', error);
    }

  };

  //handle accept or decline quote
  const acceptQuote = async (quoteId: string) => {
    try {
      const response = await axios.post(
        `http://localhost:8080/api/v1/movingexpress/quotes/${quoteId}/events`,
        {
          event: 'accept'
        }
      );

      console.log('Response:', response.data);

      setDisplayDetail(false)
      fetchData()


    } catch (error) {
      console.error('Error:', error);
    }
  }

  const menuIcon = () => {
    return (
      <svg className='pt-1' width="25" viewBox="0 0 89 75" fill="none" xmlns="http://www.w3.org/2000/svg">
        <rect width="89" height="13" fill="white" />
        <rect y="31" width="89" height="13" fill="white" />
        <rect y="62" width="89" height="13" fill="white" />
      </svg>
    )
  }

  const refreshIcon = () => {
    return (
      <svg width="15" viewBox="0 0 100 100" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path d="M50 100C36.0417 100 24.2187 95.1562 14.5312 85.4687C4.84375 75.7812 0 63.9583 0 50C0 36.0417 4.84375 24.2187 14.5312 14.5312C24.2187 4.84375 36.0417 0 50 0C57.1875 0 64.0625 1.48333 70.625 4.45C77.1875 7.41667 82.8125 11.6625 87.5 17.1875V0H100V43.75H56.25V31.25H82.5C79.1667 25.4167 74.6104 20.8333 68.8312 17.5C63.0521 14.1667 56.775 12.5 50 12.5C39.5833 12.5 30.7292 16.1458 23.4375 23.4375C16.1458 30.7292 12.5 39.5833 12.5 50C12.5 60.4167 16.1458 69.2708 23.4375 76.5625C30.7292 83.8542 39.5833 87.5 50 87.5C58.0208 87.5 65.2604 85.2083 71.7188 80.625C78.1771 76.0417 82.7083 70 85.3125 62.5H98.4375C95.5208 73.5417 89.5833 82.5521 80.625 89.5313C71.6667 96.5104 61.4583 100 50 100Z" fill="white" />
      </svg>
    )
  }

  return (
    <div>

      {/* shipment quote details pop up */}

      {displayDetail && (

        <div className='bg-[rgba(0,0,0,0.8)] w-[100vw] h-[100vh] absolute z-10'>

          <div className='flex justify-center'>

            <div className='bg-white mt-8 w-[80vw] h-[90vh] rounded-lg px-5 py-5'>

              <div style={{alignItems: "center"}} className='flex justify-between '>
                <div style={{ fontFamily: 'Bebas Neue, cursive'}} className='text-xl'>Shipment Quote | <span className='text-companyYellow'>Details</span></div>
                
                <button className="text-white bg-companyYellow w-[30px] h-[30px] text-sm rounded pb-1" onClick={()=>setDisplayDetail(false)}>
                  X
                </button>
              </div>

              <div className='mt-5 border-b  border-t'>
                <ShipmentQuoteDetails shipmentQuote={selectedQuote} />
              </div>

              <div className="flex flex-row gap-1 py-3">
                  {selectedQuote.quoteStatus !== "DECLINED" ?
                    <div className=' flex flex-row gap-3'>
                      <div><button style={{ fontFamily: 'Bebas Neue, cursive'}} id='acceptBtn' onClick={() => { acceptQuote(selectedQuote.quoteId) }} className='px-2.5 py-1 bg-companyYellow text-white rounded text-sm'>Accept</button></div>
                      <div><button style={{ fontFamily: 'Bebas Neue, cursive'}} id='declineBtn' onClick={() => { declineQuote(selectedQuote.quoteId) }} className='px-2.5 py-1 bg-gray-400 text-white rounded text-sm'>Decline</button></div>
                    </div>
                    : <div> </div>
                  }
              </div>
              
            </div>
            
          </div>
          
        </div>


      )}

      <div className='lg:flex'>
        <div className='w-[300px] bg-[#2D2D2D] h-[100vh] hidden lg:block py-10 lg:flex justify-center'>
          <div className='flex flex-col text-white'>
            <div>
              <img src={logo} alt="logo" className="w-24 2xl:w-32" />
            </div>

            <br />

            <div style={{ fontFamily: 'Bebas Neue, cursive', letterSpacing: "1px" }} className='pt-5 pb-5'>
              Shipment Reviewer
            </div>

            <hr />

            <div className='pt-5'>
              <button className='hover:underline underline-offset-4 decoration-2 decoration-companyYellow' style={{ fontFamily: 'Bebas Neue, cursive', letterSpacing: "1px" }}>Shipment Quotes</button>
            </div>

            <div className='pt-5'>
              <form action={`http://localhost:8080/api/v1/movingexpress/logout`} method="post">
                <button className='hover:underline underline-offset-4 decoration-2 decoration-companyYellow' style={{ fontFamily: 'Bebas Neue, cursive', letterSpacing: "1px" }} type={'submit'} >LogOut</button>
              </form>
            </div>
          </div>
        </div>

        {/* phone version nav bar */}
        <div style={{ alignItems: "center" }} className="px-5 py-4 flex justify-between lg:hidden bg-[#2D2D2D]">

          <div>
            <img src={logo} alt="logo" className="w-24 2xl:w-32" />
          </div>

          <div>
            <button onClick={() => { setMenuOpen(!menuOpen) }}>
              {menuIcon()}
            </button>
          </div>

        </div>
        <div className='absolute lg:hidden text-[gray] z-10' style={menuOpen ? { width: "100%", height: "100%", overflow: "hidden" } : { height: "0", overflow: "hidden" }}>
          <div className='bg-white shadow-2xl'>
          <Link to={"/ShipmentQuotes"}>
            <div style={{ fontFamily: 'Bebas Neue, cursive' }} className='px-5 py-2 border-b bg-white hover:bg-companyYellow hover:text-[#2D2D2D] hover:pl-10 duration-300 ease-in-out '>Shipment Quotes</div>
          </Link>
          <Link to={"/LogOut"}>
            <div style={{ fontFamily: 'Bebas Neue, cursive' }} className='px-5 py-2 border-b bg-white hover:bg-companyYellow hover:text-[#2D2D2D] hover:pl-10 duration-300 ease-in-out '>Log Out</div>
          </Link></div>
        </div>

        <div className={`w-[100%] h-[100vh] py-5 px-5 relative bg-white`}>

          <div className="flex flex-row justify-between">

            <div style={{ fontFamily: 'Bebas Neue, cursive' }} className='text-2xl'>Shipment <span className="text-companyYellow">Quotes</span></div>

            <div><button onClick={() => fetchData()} style={{ fontFamily: 'Bebas Neue, cursive', alignItems: 'center' }} className="bg-companyYellow text-white py-1 px-4 rounded-sm text-sm flex flex-row gap-2"><span>Refresh</span><span>{refreshIcon()}</span></button></div>
          </div>

          <hr className='mb-5 mt-2' />

          <div className='absolute w-[96%] max-h-96 overflow-y-auto overflow-x-auto border'>
            <table className='border w-[100%]'>

              <thead style={{ fontFamily: 'Bebas Neue, cursive' }} className='border bg-companyYellow text-white text-sm text-center'>
                <tr className=''>
                  <td className='border px-3 py-2'>Shipment Name</td>
                  <td className='border px-3 py-2'>E-Mail</td>
                  <td className='border px-3 py-2 hidden lg:table-cell'>Phone Number</td>
                  <td className='border px-3 py-2 hidden lg:table-cell'>First Name</td>
                  <td className='border px-3 py-2 hidden lg:table-cell'>Last Name</td>
                  <td className='border px-3 py-2'>Status</td>
                  <td className='border px-3 py-2'></td>
                </tr>
              </thead>

              <div>

              </div>

              <tbody>

                {
                  pendingQuotes.map((quote: any) => (

                    <tr id={quote.quoteId} className='text-center text-sm'>
                      {//@ts-ignore
                        <td name={quote.shipmentName} className='border px-3'>{quote.shipmentName}</td>}
                      <td className='border px-3'>{quote.emailAddress}</td>
                      <td className='border px-3 hidden lg:table-cell'>{quote.phoneNumber}</td>
                      <td className='border px-3 hidden lg:table-cell'>{quote.firstName}</td>
                      <td className='border px-3 hidden lg:table-cell'>{quote.lastName}</td>
                      <td id={`pending-${quote.quoteId}`} className='border px-3 '>{quote.quoteStatus}</td>
                      <td className='border px-3 '><button id={`btn-${quote.quoteId}`} onClick={() => { getQuoteDetails(quote.quoteId) }} style={{ fontFamily: 'Bebas Neue, cursive' }} className="bg-companyYellow text-white py-1 px-10 rounded-sm text-sm">View</button></td>
                    </tr>

                  ))
                }

                {
                  declinedQuotes.map((quote: any) => (
                    //@ts-ignore
                    <tr name={quote.name} id={quote.quoteId} className='text-center text-sm'>
                      {//@ts-ignore
                        <td name={quote.shipmentName} className='border px-3'>{quote.shipmentName}</td>}
                      <td className='border px-3'>{quote.emailAddress}</td>
                      <td className='border px-3 hidden lg:table-cell'>{quote.phoneNumber}</td>
                      <td className='border px-3 hidden lg:table-cell'>{quote.firstName}</td>
                      <td className='border px-3 hidden lg:table-cell'>{quote.lastName}</td>
                      <td className='border px-3 '>{quote.quoteStatus}</td>
                      {//@ts-ignore
                        <td name={quote.shipmentName} className='border px-3 '><button id={`btn-${quote.quoteId}`} onClick={() => { getQuoteDetails(quote.quoteId) }} style={{ fontFamily: 'Bebas Neue, cursive' }} className="bg-companyYellow text-white py-1 px-10 rounded-sm text-sm">View</button></td>
                      }</tr>

                  ))
                }

              </tbody>

            </table>
          </div>
        </div>
      </div >

    </div >
  )
}

export default ShipmentReviewerPage