import React from 'react'
import logo from '../Images/ME_Logo.png'
import Footer from '../Components/Footer'
import { Link } from 'react-router-dom'

function LandingPage() {
    return (
        <div className="LandingPContainer">
            {/* Nav/Top section */}
            <div className="flex justify-between px-10 py-10">
                <div
                    style={{ fontFamily: 'Bebas Neue, cursive' }}
                    className="text-white text-lg"
                >
                    <img src={logo} className="w-24 2xl:w-32" />
                </div>

                <div className="flex gap-5 align-middle">
                    {/* <div>
            <a style={{fontFamily : "Bebas Neue, cursive"}} className="border-[3px] border-companyYellow px-7 py-2 rounded-md text-white" href='/SignUp'>Sign Up</a>
          </div> */}
                    <div>
                        <a
                            style={{ fontFamily: 'Bebas Neue, cursive' }}
                            id="signinsignuplandingpage"
                            className="border-[3px] border-companyYellow px-7 py-2 rounded-md text-white"
                            href="http://localhost:8080/oauth2/authorization/okta"
                        >
                            Login | Sign Up
                        </a>
                    </div>
                </div>
            </div>

            <div className="flex px-[5%] mt-10 2xl:mt-28 xsm:flex-col lg:flex-row">
                <div className="flex flex-col gap-5 w-[100%] lg:w-[50%]">
                    <div
                        style={{ fontFamily: 'Bebas Neue, cursive' }}
                        className="text-white text-6xl sm:text-7xl 2xl:text-[120px]"
                    >
                        <div>Move Faster with Us</div>
                        <div className="text-companyYellow">
                            {' '}
                            Moving Express
                        </div>
                    </div>

                    <div className="text-white font-light text-sm opacity-90 pr-[10%] mt-5 mb-2 2xl:text-lg">
                        Are you in search of a reliable company to facilitate
                        your move seamlessly? Look no further! Moving Express is
                        here to assist! Please click on 'Shipment Quote' to
                        receive a personalized quote for your relocation.
                    </div>

                    <div>
                        {/* <a href="/ShipmentQuote" style={{fontFamily : "Bebas Neue, cursive"}} id="shipmentQuoteBtn" className="text-white bg-companyYellow px-6 py-2 rounded-full">Shipment Quote</a> */}
                        <Link
                            style={{ fontFamily: 'Bebas Neue, cursive' }}
                            id="shipmentQuoteBtn"
                            className="text-white bg-companyYellow px-6 py-2 rounded-full"
                            to={'/ShipmentQuote'}
                        >
                            Shipment Quote
                        </Link>
                    </div>
                </div>

                <div className="flex justify-center w-[100%] lg:w-[50%] h-[30%]">
                    <div className="bg-white shadow-md rounded-md py-6 px-6 mt-16 lg:mt-0 w-[500px]">
                        <div
                            style={{ fontFamily: 'Bebas Neue, cursive' }}
                            className="mb-2 opacity-75"
                        >
                            Shipment Look Up
                        </div>

                        <div className="hidden lg:block mb-4 opacity-50 text-sm">
                            Do you have a shipment observer code? Enter it below
                            to view details.
                        </div>

                        <div className="flex gap-5 w-[100%]">
                            <div>
                                <input
                                    type="text"
                                    className="border px-4 py-[6px] w-[300px] border-[lightgray] text-sm"
                                    placeholder=" ex. XVMP35LP"
                                />
                            </div>

                            <div>
                                <button
                                    style={{
                                        fontFamily: 'Bebas Neue, cursive',
                                    }}
                                    className="text-white text-[13px] bg-companyYellow py-2 w-[100px] rounded-md shadow-md"
                                >
                                    Verify Code
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default LandingPage
