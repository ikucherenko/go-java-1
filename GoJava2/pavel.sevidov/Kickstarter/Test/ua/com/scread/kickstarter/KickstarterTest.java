package ua.com.scread.kickstarter;

import static org.mockito.Mockito.*;

import org.junit.Test;

public class KickstarterTest {
		
    IO io = mock(IO.class);
    QuoteGenerator quote = mock(QuoteGenerator.class);

    @Test
	public void shouldBeQuote_whenStartApplication() {
	    Model model = new Model();
	    when(quote.getQuote()).thenReturn("quote");
	    when(io.read()).thenReturn(0);
	    
		KickstarterRunner kickstarter = new KickstarterRunner(model, io, quote);
		
		kickstarter.run();
		
		verify(io).print("quote\n");        
	}
	
	@Test
    public void shouldBeCategories_whenStartApplication() {
	    Categories categories = new InMemoryCategories();
	    Category category1 = new Category("category1");
        Category category2 = new Category("category2");
        categories.add(category1);
        categories.add(category2);
        
        Projects projects = new Projects();
  
        Model model = new Model(categories, projects);
        when(quote.getQuote()).thenReturn("quote");
        when(io.read()).thenReturn(1, 0);
        
        KickstarterRunner kickstarter = new KickstarterRunner(model, io, quote);
        
        kickstarter.run();
        
        verify(io).print("quote\n");
        verify(io, times(2)).print("\nChoose category: ");
        verify(io, times(2)).print("[1 - category1, 2 - category2]");
        verify(io).print("You choosed: category1\n");
    }
	
	@Test
    public void shouldBeProjects_whenChoosedCategory() {
        Categories categories = new InMemoryCategories();
        Category category1 = new Category("category1");
        Category category2 = new Category("category2");
        categories.add(category1);
        categories.add(category2);
        
        Projects projects = new Projects();
        Bonuses bonuses = new Bonuses(new Bonus(50, "Description"));
        FAQs faqs = new FAQs(new FAQ("Question", "Answer"));
        Project project = new Project("Project", "Description", 10, 10, 
                new AdditionalInfo("Some history", "Video link", bonuses, faqs));
        project.setCategory(category1);
        projects.add(project);
      
        Model model = new Model(categories, projects);
        when(quote.getQuote()).thenReturn("quote");
        when(io.read()).thenReturn(1, 0, 0);
        
        KickstarterRunner kickstarter = new KickstarterRunner(model, io, quote);
        
        kickstarter.run();
        
        verify(io).print("Project\n");
        verify(io).print("Description\n");
    }
	
	@Test
    public void shouldCloseMessage_whenClosedApp() {
              
        Model model = new Model();
        when(io.read()).thenReturn(0);
        
        KickstarterRunner kickstarter = new KickstarterRunner(model, io, quote);
        
        kickstarter.run();
        
        verify(io).print("Thanks for using my program!");
    }
	
	@Test
    public void shouldProjectDetails_whenChoosedProject() {
        Categories categories = new InMemoryCategories();
        Category category1 = new Category("category1");
        categories.add(category1);
        
        Projects projects = new Projects();
        Bonuses bonuses = new Bonuses(new Bonus(50, "Description"));
        FAQs faqs = new FAQs(new FAQ("Question", "Answer"));
        Project project = new Project("Project", "Description", 10, 10, 
                new AdditionalInfo("Some history", "Video link", bonuses, faqs));
        project.setCategory(category1);
        projects.add(project);
      
        Model model = new Model(categories, projects);
        when(quote.getQuote()).thenReturn("quote");
        when(io.read()).thenReturn(1, 1, 0, 0, 0);
        
        KickstarterRunner kickstarter = new KickstarterRunner(model, io, quote);
        
        kickstarter.run();
        
        verify(io).print("History: Some history\n");
        verify(io).print("\nFrequently Asked Questions: \n");
    }
	
	@Test
	public void shouldAddDonation_whenAddedDonation() {
	    Model model = new Model();
	    model.init();
        IO io = mock(IO.class);
        QuoteGenerator quote = mock(QuoteGenerator.class);
        when(io.read()).thenReturn(3, 1, 1, 2, 100500, 0, 0, 0);
        when(io.readString()).thenReturn("Name");
        when(io.readLong()).thenReturn((long) 1234567890);
        KickstarterRunner kickstarter = new KickstarterRunner(model, io, quote);
        
        kickstarter.run();
        
        verify(io, times(4)).print("Already collected 0.0 UAH for 10 days\n");
        verify(io, times(2)).print("Already collected 100500.0 UAH for 10 days\n");
	}
	
	@Test
	public void shouldAddDonation_whenChoosedBonus() {
	    Model model = new Model();
        model.init();
        when(io.read()).thenReturn(3, 1, 1, 1, 0, 0, 0);
        when(io.readString()).thenReturn("Name");
        when(io.readLong()).thenReturn((long) 1234567890);
        KickstarterRunner kickstarter = new KickstarterRunner(model, io, quote);
        
        kickstarter.run();
        
        verify(io, times(4)).print("Already collected 0.0 UAH for 10 days\n");
        verify(io, times(2)).print("Already collected 10.0 UAH for 10 days\n");
	}
	
	@Test
	public void shouldAddFAQ_whenAddedFAQ() {
	    Model model = new Model();
        model.init();
        when(io.read()).thenReturn(3, 1, 2, 0, 0, 0);
        when(io.readString()).thenReturn("Asked question?");
        KickstarterRunner kickstarter = new KickstarterRunner(model, io, quote);
        
        kickstarter.run();
        
        verify(io).print("Asked question?\n");
	}
	 
}
