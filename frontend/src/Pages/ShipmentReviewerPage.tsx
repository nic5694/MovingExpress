import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import logo from '../Images/ME_Logo.png'

function ShipmentReviewerPage() {
  const[menuOpen,setMenuOpen] = useState(false)

  const menuIcon = () => {
    return(
      <svg className='pt-1' width="25" viewBox="0 0 89 75" fill="none" xmlns="http://www.w3.org/2000/svg">
        <rect width="89" height="13" fill="white"/>
        <rect y="31" width="89" height="13" fill="white"/>
        <rect y="62" width="89" height="13" fill="white"/>
      </svg>
    )
  }

  return (
    <div>

      <div className='lg:flex'>
        <div className='w-[300px] bg-[#2D2D2D] h-[100vh] hidden lg:block py-10 lg:flex justify-center'>
          <div className='flex flex-col text-white'>
            <div>
                <img src={logo} alt="logo" className="w-24 2xl:w-32" />
            </div>

            <br />

            <div style={{ fontFamily: 'Bebas Neue, cursive',letterSpacing: "1px" }} className='pt-5 pb-5'>
              Shipment Reviewer
            </div>

            <hr />

            <div className='pt-5'>
              <button className='hover:underline underline-offset-4 decoration-2 decoration-companyYellow' style={{ fontFamily: 'Bebas Neue, cursive',letterSpacing: "1px" }}>Shipment Quotes</button>
            </div>

            <div className='pt-5'>
              <button className='hover:underline underline-offset-4 decoration-2 decoration-companyYellow' style={{ fontFamily: 'Bebas Neue, cursive',letterSpacing: "1px" }} >LogOut</button>
            </div>
          </div>
        </div>

        {/* phone version nav bar */}
        <div style={{alignItems: "center"}} className="px-5 py-4 flex justify-between lg:hidden bg-[#2D2D2D]">

          <div>
              <img src={logo} alt="logo" className="w-24 2xl:w-32" />
          </div>

          <div>
            <button onClick={() => {setMenuOpen(!menuOpen)}}>
              {menuIcon()}
            </button>
          </div>

        </div>
        <div className='absolute lg:hidden text-[gray]' style={menuOpen ? {width: "100%" ,height: "100%", overflow: "hidden"} : {height: "0", overflow: "hidden"}}>
          <Link to={"/ShipmentQuotes"}>
            <div style={{ fontFamily: 'Bebas Neue, cursive' }} className='px-5 py-2 border-b bg-white hover:bg-companyYellow hover:text-[#2D2D2D]'>Shipment Quotes</div>
          </Link>
          <Link to={"/LogOut"}>
            <div style={{ fontFamily: 'Bebas Neue, cursive' }} className='px-5 py-2 border-b bg-white hover:bg-companyYellow hover:text-[#2D2D2D]'>Log Out</div>
          </Link>
        </div>

        <div className='w-[100%] bg-[white] h-[100vh] '> 
        </div>

      </div>

      

    </div>
  )
}

export default ShipmentReviewerPage