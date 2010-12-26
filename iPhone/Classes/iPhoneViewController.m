#import "iPhoneViewController.h"

@implementation iPhoneViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
	MapView* mapView = [[[MapView alloc] initWithFrame: CGRectMake(0, 0, self.view.frame.size.width, self.view.frame.size.height)] autorelease];
	
	[self.view addSubview:mapView];
}

#pragma mark -
#pragma mark - Dealloc

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
}

- (void)viewDidUnload {
}

- (void)dealloc {
    [super dealloc];
}

@end
