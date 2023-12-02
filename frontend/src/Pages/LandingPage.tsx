import React from 'react'
import logo from '../Images/ME_Logo.png'
import Footer from '../Components/Footer'

function LandingPage() {
  return (
    <div className='LandingPContainer'>

        {/* Nav/Top section */}
        <div className="flex justify-between px-10 py-10">

          <div style={{fontFamily : "Bebas Neue, cursive"}} className="text-white text-lg" >
            <img src={logo} className='w-24 2xl:w-32' />
          </div>
          
          <div className="flex gap-5 align-middle">
            <div>
              <a style={{fontFamily : "Bebas Neue, cursive"}} className="border-[3px] border-companyYellow px-7 py-2 rounded-md text-white" href='/SignUp'>Sign Up</a>
            </div>
            <div>
              <a style={{fontFamily : "Bebas Neue, cursive"}} className="border-[3px] border-companyYellow px-7 py-2 rounded-md text-white" href='/Login'>Login</a>
            </div>
          </div>

        </div>

        <div className="flex flex-col justify-center gap-10 mt-5 2xl:mt-28">
          
          <div style={{fontFamily : "Bebas Neue, cursive"}} className="text-white text-center text-6xl sm:text-7xl 2xl:text-9xl">
            <div>Move Faster with Us:</div>
            <div className="text-companyYellow"> Moving Express</div>
          </div>
          


          <div className="text-white text-center font-light text-sm opacity-90 xsm:px-[10%] sm:px-[30%] 2xl:text-lg 2xl:px-[33%]">
            Are you in search of a reliable company to facilitate your move seamlessly? Look no further! 
            Moving Express is here to assist! Please click on 'Shipment Quote' to receive 
            a personalized quote for your relocation.
          </div>

          <div className='flex justify-center'>
            <a href="/ShipmentQuote" style={{fontFamily : "Bebas Neue, cursive"}} className="text-white bg-companyYellow px-6 py-2 rounded-full">Shipment Quote</a>
          </div>
          
        </div>

        <div className='flex justify-center mt-14'>
          <div className='bg-white shadow-md rounded-md flex justify-center py-6 px-6'>
            <div>
              <div style={{fontFamily : "Bebas Neue, cursive"}} className='mb-2 opacity-75'>Shipment Look Up</div>
              <div className='flex justify-center gap-5'>
                  <div>
                    <input style={{width: "45vw", height: "100%"}} type="text" className='border pl-5' placeholder=' ex. XVMP35LP' />
                  </div>
                  <div>
                    <button style={{fontFamily : "Bebas Neue, cursive"}} className="text-white text-[13px] bg-companyYellow px-6 py-2 rounded-md shadow-md">Verify Code</button>
                  </div>
              </div>
            </div>
          </div>
        </div>
    </div>
  )
}

export default LandingPage