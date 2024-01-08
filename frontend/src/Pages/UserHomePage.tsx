import React from 'react'
import UserHomeNav from '../Components/UserHomeNav'
import ScrollToTopBtn from '../Components/ScrollToTopBtn'
import { Link } from 'react-router-dom'
import truckIcon from '../Images/truck icon.png'
import ShipmentQuoteIMG from '../Images/shipment quote.png'
import ApprovedIMG from '../Images/Created.png'
import InventoryIMG from '../Images/inventory.jpg'
import ReadyIMG from '../Images/ready.jpg'

function UserHomePage() {
  return (
    <div>
      <ScrollToTopBtn />

      <div className='UserHomePageContainer bg-[lightgray]'>
        <UserHomeNav />

        <div className='flex flex-col gap-5 px-8 py-20'>

          <div>
            <div style={{ fontFamily: 'Bebas Neue, cursive' }} className='text-6xl text-white'>Welcome To</div>
            <div style={{ fontFamily: 'Bebas Neue, cursive' }} className='text-6xl text-companyYellow'>Moving Express</div>
          </div>

          <div className='text-white font-light text-sm md:w-[50%] xl:w-[30%]'>
            We're here to make your move hassle-free. Explore our services for a personalized solution. Thanks for choosing Moving Express.
          </div>

          <div className='flex flex-row gap-5 mt-3'>
            <Link to={"/"}>
              <div style={{ fontFamily: 'Bebas Neue, cursive' }} className='text-white text-sm bg-companyYellow border-2 px-5 py-1 border-companyYellow rounded hover:translate-y-[-5px] duration-300 ease-in-out'>Button 1</div>
            </Link>

            <Link to={"/"}>
              <div style={{ fontFamily: 'Bebas Neue, cursive' }} className='text-white text-sm border-2 px-5 py-1 border-companyYellow rounded hover:translate-y-[-5px] duration-300 ease-in-out'>Button 2</div>
            </Link>
          </div>

        </div>

      </div>

      <div className='pt-20 text-center px-[10%] pb-20'>
        <div style={{ fontFamily: 'Bebas Neue, cursive',alignItems: "center" }} className='text-2xl flex justify-center'>Shipment <span className='text-companyYellow pl-1 pr-3'>Process</span> <img className='w-14' src={truckIcon} /></div>
        <div className="py-3 font-light text-sm opacity-90">The following tree diagram provides a clear and concise visual representation of the step-by-step process involved in making a shipment. This graphical illustration is designed to help you navigate and understand the various stages and decision points in the shipping process.</div>
      </div>

      <hr className='mx-[10%] border-companyYellow' />

      <div style={{alignItems: "center"}} className='flex flex-col py-10 gap-20 px-[10%]'>

        <div style={{alignItems: "center"}}  className='flex flex-row gap-20'>

          <div className='flex flex-col gap-3'>
            <div style={{ fontFamily: 'Bebas Neue, cursive', alignItems: "end"}} className='flex flex-row gap-2'>
              <div style={{alignItems: "center"}} className='bg-white border-[6px] border-companyYellow w-10 h-10 rounded-full font-bold flex justify-center'>1</div>
              <div className='text-2xl'>Shipment Quote</div>
            </div>
            <div className='lg:w-[400px] font-light text-sm opacity-90'>This graphical illustration is designed to help you navigate and understand the various stages and decision points in the shipping process, ensuring a smooth and efficient journey for your goods from point A to point B.</div>

            <div className='mt-3'>
              <Link to={'/UserShipmentQuote'}>
                <div style={{ fontFamily: 'Bebas Neue, cursive' }} className='text-companyYellow text-sm border-2 px-5 py-1 w-[110px] border-companyYellow rounded hover:bg-[#2D2D2D] hover:text-white duration-300 ease-in-out shadow-lg'>Make a Quote</div>
              </Link>
            </div>
          </div>

          <div className='hidden md:block'>
            <img className='bg-white shadow-lg border rounded w-[400px]' src={ShipmentQuoteIMG}/>
          </div>

        </div>

        <div style={{alignItems: "center"}} className='flex flex-row gap-20'>

          <div className='hidden md:block'>
            <img className='bg-white shadow-lg border rounded w-[400px]' src={ApprovedIMG}/>
          </div>
          
          <div className='flex flex-col gap-3'>
            <div style={{ fontFamily: 'Bebas Neue, cursive', alignItems: "end"}} className='flex flex-row gap-2'>
              <div style={{alignItems: "center"}} className='bg-white border-[6px] border-companyYellow w-10 h-10 rounded-full font-bold flex justify-center'>2</div>
              <div className='text-2xl'>Shipment Created</div>
            </div>
            <div className='lg:w-[400px] font-light text-sm opacity-90'>This graphical illustration is designed to help you navigate and understand the various stages and decision points in the shipping process, ensuring a smooth and efficient journey for your goods from point A to point B.</div>
         
            <div className='mt-3'>
              <Link to={'/Shipments'}>
                <div style={{ fontFamily: 'Bebas Neue, cursive' }} className='text-companyYellow text-sm border-2 px-5 py-1 w-[120px] border-companyYellow rounded hover:bg-[#2D2D2D] hover:text-white duration-300 ease-in-out shadow-lg'>View Shipments</div>
              </Link>
            </div>
          
          </div>

        </div>

        <div style={{alignItems: "center"}}  className='flex flex-row gap-20'>

          <div className='flex flex-col gap-3'>
            <div style={{ fontFamily: 'Bebas Neue, cursive', alignItems: "end"}} className='flex flex-row gap-2'>
              <div style={{alignItems: "center"}} className='bg-white border-[6px] border-companyYellow w-10 h-10 rounded-full font-bold flex justify-center'>3</div>
              <div className='text-2xl'>Start Filling Your Inventory !</div>
            </div>
            <div className='lg:w-[400px] font-light text-sm opacity-90'>This graphical illustration is designed to help you navigate and understand the various stages and decision points in the shipping process, ensuring a smooth and efficient journey for your goods from point A to point B.</div>
          </div>

          <div className='hidden md:block'>
            <img className='bg-white shadow-lg border rounded w-[400px]' src={InventoryIMG}/>
          </div>

        </div>

        <div style={{alignItems: "center"}} className='flex flex-row gap-20'>

          <div className='hidden md:block'>
            <img className='bg-white shadow-lg border rounded w-[400px]' src={ReadyIMG}/>
          </div>

          <div className='flex flex-col gap-3 border-b-[40px]'>
            <div style={{ fontFamily: 'Bebas Neue, cursive', alignItems: "end"}} className='flex flex-row gap-2'>
              <div style={{alignItems: "center"}} className='bg-white border-[6px] border-companyYellow w-10 h-10 rounded-full font-bold flex justify-center'>4</div>
              <div className='text-2xl'>Ready For Departure</div>
            </div>
            <div className='lg:w-[400px] font-light text-sm opacity-90'>This graphical illustration is designed to help you navigate and understand the various stages and decision points in the shipping process, ensuring a smooth and efficient journey for your goods from point A to point B.</div>
            
            <div className='mt-5 truck w-[100px]'>
              <img className='w-20' src={truckIcon} />
            </div>
          
          </div>
        </div>

      </div>

      <hr className='mx-[10%] pb-20 border-companyYellow' />


      
    </div>
  )
}

export default UserHomePage