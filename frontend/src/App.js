import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import {
  BrowserRouter as Router,
  Switch,
  Route,
} from "react-router-dom";
import RegistrationFormContainer from './Containers/RegistrationFormContainer';
import HomePageContainer from './Containers/HomePageContainer';
import LoginContainer from './Containers/LoginContainer';
import PublicProfilesContainer from './Containers/PublicProfilesContainer';
import NewFeedContainer from './Containers/NewFeedContainer';
import FollowRequestsContainer from './Containers/FollowRequestsContainer';
import MessageContainer from './Containers/MessageContainer';
import JobOffersContainer from './Containers/JobOffersContainer';
import CreateJobOfferContainer from './Containers/CreateJobOfferContainer';
import UpdateUserContainer from './Containers/UpdateUserContainer';
import FollowingUsersContainer from './Containers/FollowingUsersContainer';
import NotificationsContainer from './Containers/NotificationsContainer';
import RegistrationFormAgentContainer from './Containers/RegistrationFormAgentContainer';
import CreateCompanyContainer from './Containers/CreateCompanyContainer';
import CompanyRequests from './Components/Common/CompanyRequests';
import CompanyRequestsContainer from './Containers/CompanyRequestsContainer';
import CompaniesByUserContainer from './Containers/CompaniesByUserContainer';
import AllCompaniesContainer from './Containers/AllCompaniesContainer';
import CompanyHomePageContainer from './Containers/CompanyHomePageContainer';
import JobOfferCommentsContainer from './Containers/JobOfferCommentsContainer';
import AllProfilesContainer from './Containers/AllProfilesContainer';
import MyHomePageContainer from './Containers/MyHomePageContainer';


function App() {
  return (
    <div className="container">
      <Router>
        <Switch>
          <Route path="/registration">
            <RegistrationFormContainer></RegistrationFormContainer>
          </Route>
          <Route path="/homePage/:username">
            <HomePageContainer></HomePageContainer>
          </Route>
          <Route path="/login">
            <LoginContainer></LoginContainer>
          </Route>
          <Route path="/publicProfiles">
            <PublicProfilesContainer></PublicProfilesContainer>
          </Route>
          <Route path="/newFeed">
            <NewFeedContainer></NewFeedContainer>
          </Route>
          <Route path="/followRequests">
            <FollowRequestsContainer></FollowRequestsContainer>
          </Route>
          <Route path="/messages">
            <MessageContainer></MessageContainer>
          </Route>
          <Route path="/jobOffers">
            <JobOffersContainer></JobOffersContainer>
          </Route>
          <Route path="/createJobOffer">
            <CreateJobOfferContainer></CreateJobOfferContainer>
          </Route>
          <Route path="/updateUser">
            <UpdateUserContainer></UpdateUserContainer>
          </Route>
          <Route path="/following">
            <FollowingUsersContainer></FollowingUsersContainer>
          </Route>
          <Route path="/notifications">
            <NotificationsContainer></NotificationsContainer>
          </Route>
          <Route path="/registrationAgent">
            <RegistrationFormAgentContainer></RegistrationFormAgentContainer>
          </Route>
          <Route path="/createCompany">
            <CreateCompanyContainer></CreateCompanyContainer>
          </Route>
          <Route path="/companyRequests">
            <CompanyRequestsContainer></CompanyRequestsContainer>
          </Route>
          <Route path="/companiesByUser">
            <CompaniesByUserContainer></CompaniesByUserContainer>
          </Route>
          <Route path="/allCompanies">
            <AllCompaniesContainer></AllCompaniesContainer>
          </Route>
          <Route path="/companyHomePage">
            <CompanyHomePageContainer></CompanyHomePageContainer>
          </Route>
          <Route path="/jobOfferComments">
            <JobOfferCommentsContainer></JobOfferCommentsContainer>
          </Route>
          <Route path="/allProfiles">
            <AllProfilesContainer></AllProfilesContainer>
          </Route>
          <Route path="/myHomePage">
            <MyHomePageContainer></MyHomePageContainer>
          </Route>

        </Switch>
      </Router>
    </div>
  );
}

export default App;
